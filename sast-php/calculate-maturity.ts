/**
 * PHP Benchmark 成熟度达成度计算脚本
 *
 * 读取所有 config.json + phpbenchmark-expect.json，
 * 按 compose 规则判断每个 scene 通过/失败，按 level 汇总输出报告。
 *
 * 用法：npx ts-node calculate-maturity.ts [--expect <expect.json路径>] [--output <输出路径>]
 */

import * as fs from 'fs'
import * as path from 'path'

// ========== 类型定义 ==========

interface SceneEntry {
  compose: string
  scene: string
}

interface SceneLevel {
  level: string
  scene_list: SceneEntry[]
}

interface EvaluationGroup {
  evaluation_item: string
  scene_levels: SceneLevel[]
}

/** config.json 顶层结构：key 为分组名，value 为评价项数组 */
type ConfigJson = Record<string, EvaluationGroup[]>

interface SceneResult {
  evaluationItem: string
  level: string
  scene: string
  compose: string
  passed: boolean
  details: string[]
  configDir: string
}

interface LevelStat {
  total: number
  passed: number
}

// ========== 参数解析 ==========

const args = process.argv.slice(2)
function getArg(flag: string, defaultValue: string): string {
  const idx = args.indexOf(flag)
  if (idx !== -1 && idx + 1 < args.length) {
    return args[idx + 1]
  }
  return defaultValue
}

const CASE_DIR = path.resolve(__dirname, 'case')
const DEFAULT_EXPECT = path.resolve(__dirname, '../../yasa2/test/php/expect/phpbenchmark-expect.json')
const DEFAULT_OUTPUT = path.resolve(__dirname, '../../tasks/dev/php-benchmark-maturity/MATURITY_REPORT.md')

const expectPath = getArg('--expect', DEFAULT_EXPECT)
const outputPath = getArg('--output', DEFAULT_OUTPUT)

// ========== 1. 读取 expect 文件，构建检出状态 map ==========

if (!fs.existsSync(expectPath)) {
  console.error(`expect 文件不存在: ${expectPath}`)
  process.exit(1)
}

const expectData: Record<string, string> = JSON.parse(fs.readFileSync(expectPath, 'utf-8'))
const detectionMap = new Map<string, boolean>()

for (const [key, value] of Object.entries(expectData)) {
  // 有 findings = 被检出
  detectionMap.set(key, !value.includes('No findings!'))
}

console.log(`读取 expect 文件: ${Object.keys(expectData).length} 条结果`)

// ========== 2. 递归查找所有 config.json ==========

function findConfigFiles(dir: string): string[] {
  const results: string[] = []
  const entries = fs.readdirSync(dir, { withFileTypes: true })
  for (const entry of entries) {
    const fullPath = path.join(dir, entry.name)
    if (entry.isDirectory()) {
      results.push(...findConfigFiles(fullPath))
    } else if (entry.name === 'config.json') {
      results.push(fullPath)
    }
  }
  return results
}

const configFiles = findConfigFiles(CASE_DIR).sort()
console.log(`找到 ${configFiles.length} 个 config.json`)

// ========== 3. 解析 compose 规则，判断 scene 通过/失败 ==========

const results: SceneResult[] = []

for (const cf of configFiles) {
  const caseDir = path.dirname(cf)
  const relCaseDir = path.relative(CASE_DIR, caseDir)
  const expectPrefix = `/benchmarks/sast-php/case/${relCaseDir}`

  const data: ConfigJson = JSON.parse(fs.readFileSync(cf, 'utf-8'))

  for (const [, items] of Object.entries(data)) {
    for (const item of items) {
      const evalItem = item.evaluation_item
      for (const sl of item.scene_levels) {
        const level = sl.level
        for (const sceneEntry of sl.scene_list) {
          const { compose, scene } = sceneEntry

          // 解析 compose: "T_case.php && !F_case.php"
          const conditions = compose.split('&&').map((c: string) => c.trim())
          let scenePassed = true
          const details: string[] = []

          for (const cond of conditions) {
            const negated = cond.startsWith('!')
            const ref = negated ? cond.slice(1) : cond

            let expectKey: string
            if (ref.includes('/')) {
              // 跨文件: "autoload_001_T/main.php" -> 取目录名
              const dirName = ref.split('/')[0]
              expectKey = `${expectPrefix}/${dirName}`
            } else {
              // 单文件: "xxx_001_T.php" -> 去掉 .php
              const caseName = ref.replace(/\.php$/, '')
              expectKey = `${expectPrefix}/${caseName}`
            }

            const detected = detectionMap.get(expectKey)

            if (detected === undefined) {
              details.push(`MISSING: ${expectKey}`)
              scenePassed = false
              continue
            }

            if (negated) {
              // !F_case: 未检出 = 通过(阴性正确)；被检出 = 失败(误报)
              const condPassed = !detected
              const status = condPassed ? 'OK(未检出)' : 'FAIL(误报)'
              details.push(`${cond}: ${status}`)
              if (!condPassed) scenePassed = false
            } else {
              // T_case: 被检出 = 通过；未被检出 = 失败
              const condPassed = detected
              const status = condPassed ? 'OK(已检出)' : 'FAIL(未检出)'
              details.push(`${cond}: ${status}`)
              if (!condPassed) scenePassed = false
            }
          }

          results.push({
            evaluationItem: evalItem,
            level,
            scene,
            compose,
            passed: scenePassed,
            details,
            configDir: relCaseDir,
          })
        }
      }
    }
  }
}

// ========== 4. 按 level 统计 ==========

const levelStats = new Map<string, LevelStat>()
const evalStats = new Map<string, LevelStat>()

for (const r of results) {
  // 按 level 汇总
  if (!levelStats.has(r.level)) {
    levelStats.set(r.level, { total: 0, passed: 0 })
  }
  const ls = levelStats.get(r.level)!
  ls.total++
  if (r.passed) ls.passed++

  // 按 evaluation_item + level 汇总
  const evalKey = `${r.evaluationItem}|${r.level}`
  if (!evalStats.has(evalKey)) {
    evalStats.set(evalKey, { total: 0, passed: 0 })
  }
  const es = evalStats.get(evalKey)!
  es.total++
  if (r.passed) es.passed++
}

// ========== 5. 生成报告 ==========

const lines: string[] = []
const now = new Date().toISOString().slice(0, 10)

lines.push('# PHP Benchmark 成熟度达成报告')
lines.push('')
lines.push(`> 基于 phpbenchmark-expect.json（${Object.keys(expectData).length} 条结果）和 ${configFiles.length} 个 config.json 计算`)
lines.push('>')
lines.push(`> 生成时间：${now}`)
lines.push('')

// 按 level 汇总表
lines.push('## 按等级汇总')
lines.push('')
lines.push('| Level | 总 Scene 数 | 通过数 | 通过率 |')
lines.push('|-------|------------|--------|--------|')

let totalAll = 0
let passedAll = 0
const sortedLevels = Array.from(levelStats.keys()).sort()
for (const l of sortedLevels) {
  const s = levelStats.get(l)!
  const rate = s.total > 0 ? (s.passed / s.total * 100).toFixed(1) + '%' : '0.0%'
  lines.push(`| ${l} | ${s.total} | ${s.passed} | ${rate} |`)
  totalAll += s.total
  passedAll += s.passed
}
const totalRate = totalAll > 0 ? (passedAll / totalAll * 100).toFixed(1) + '%' : '0.0%'
lines.push(`| **总计** | **${totalAll}** | **${passedAll}** | **${totalRate}** |`)
lines.push('')

// 等级说明
lines.push('### 等级说明')
lines.push('')
lines.push('| 等级 | 核心能力 |')
lines.push('|------|---------|')
lines.push('| 1 级 基础级 | 语言特性识别，无数据流 |')
lines.push('| 2 级 标准级 | 单应用数据流分析，对象级粒度 |')
lines.push('| 3 级 增强级 | 内存建模，字段级粒度，有限动态特性 |')
lines.push('| 4 级 卓越级 | 跨应用、三方包、精确数值追踪 |')
lines.push('')

// 按评价项细分
lines.push('## 按评价项细分')
lines.push('')
lines.push('| 评价项 | Level | 总 Scene | 通过 | 通过率 |')
lines.push('|--------|-------|---------|------|--------|')

const sortedEvalKeys = Array.from(evalStats.keys()).sort((a, b) => {
  const [, la] = a.split('|')
  const [, lb] = b.split('|')
  if (la !== lb) return la.localeCompare(lb)
  return a.localeCompare(b)
})

for (const key of sortedEvalKeys) {
  const [evalItem, level] = key.split('|')
  const s = evalStats.get(key)!
  const rate = s.total > 0 ? (s.passed / s.total * 100).toFixed(1) + '%' : '0.0%'
  lines.push(`| ${evalItem} | ${level} | ${s.total} | ${s.passed} | ${rate} |`)
}
lines.push('')

// 失败 scene 详情
lines.push('## 失败 Scene 详情')
lines.push('')

const failed = results.filter(r => !r.passed)
if (failed.length === 0) {
  lines.push('所有 scene 均通过。')
} else {
  lines.push(`共 ${failed.length} 个 scene 未通过：`)
  lines.push('')

  const sortedFailed = failed.sort((a, b) => {
    if (a.level !== b.level) return a.level.localeCompare(b.level)
    return a.evaluationItem.localeCompare(b.evaluationItem)
  })

  for (const r of sortedFailed) {
    lines.push(`### L${r.level} | ${r.evaluationItem}`)
    lines.push(`- **场景**: ${r.scene}`)
    lines.push(`- **compose**: \`${r.compose}\``)
    lines.push(`- **目录**: \`${r.configDir}\``)
    for (const d of r.details) {
      lines.push(`  - ${d}`)
    }
    lines.push('')
  }
}

const report = lines.join('\n')

// 写入文件
const outputDir = path.dirname(outputPath)
if (!fs.existsSync(outputDir)) {
  fs.mkdirSync(outputDir, { recursive: true })
}
fs.writeFileSync(outputPath, report, 'utf-8')

console.log('')
console.log('=== 成熟度达成报告 ===')
console.log(`Level 汇总:`)
for (const l of sortedLevels) {
  const s = levelStats.get(l)!
  const rate = s.total > 0 ? (s.passed / s.total * 100).toFixed(1) + '%' : '0.0%'
  console.log(`  Level ${l}: ${s.passed}/${s.total} (${rate})`)
}
console.log(`  总计: ${passedAll}/${totalAll} (${totalRate})`)
console.log(``)
console.log(`失败 scene 数: ${failed.length}`)
console.log(`报告已写入: ${outputPath}`)
