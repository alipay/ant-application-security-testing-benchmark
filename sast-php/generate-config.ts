import * as fs from 'fs';
import * as path from 'path';

// PHP case 目录
const CASE_DIR = path.join(__dirname, 'case');

interface CaseInfo {
  fileName: string;        // 文件名（不含扩展名）或子目录名
  filePath: string;        // 完整路径（到 .php 文件或 main.php）
  realCase: boolean;       // real case = true/false -> T/F
  evaluationItem: string;
  sceneIntroduction: string;
  level: string;
  bindUrl: string;
  isTrue: boolean;         // _T 后缀
  // 从文件名解析
  baseName: string;        // 去掉 _NNN_T/_NNN_F 后的部分
  number: string;          // 编号，如 "001"
  leafDir: string;         // 所在叶子目录路径（相对 case/）
}

// 从 PHP 文件中提取注释信息
function parsePhpAnnotations(filePath: string): Omit<CaseInfo, 'fileName' | 'filePath' | 'isTrue' | 'baseName' | 'number' | 'leafDir'> | null {
  const content = fs.readFileSync(filePath, 'utf-8');
  const startMark = '// evaluation information start';
  const endMark = '// evaluation information end';

  const startIdx = content.indexOf(startMark);
  const endIdx = content.indexOf(endMark);
  if (startIdx === -1 || endIdx === -1) return null;

  const block = content.substring(startIdx, endIdx);

  const realCaseMatch = block.match(/\/\/\s*real case\s*=\s*(true|false)/);
  const evalItemMatch = block.match(/\/\/\s*evaluation item\s*=\s*(.+)/);
  const sceneMatch = block.match(/\/\/\s*scene introduction\s*=\s*(.+)/);
  const levelMatch = block.match(/\/\/\s*level\s*=\s*(\d+)/);
  const bindUrlMatch = block.match(/\/\/\s*bind_url\s*=\s*(.+)/);

  if (!realCaseMatch || !evalItemMatch || !sceneMatch || !levelMatch || !bindUrlMatch) {
    console.warn(`  警告: ${filePath} 注释不完整`);
    return null;
  }

  return {
    realCase: realCaseMatch[1].trim() === 'true',
    evaluationItem: evalItemMatch[1].trim(),
    sceneIntroduction: sceneMatch[1].trim(),
    level: levelMatch[1].trim(),
    bindUrl: bindUrlMatch[1].trim(),
  };
}

// 解析文件名中的 baseName 和编号
function parseFileName(name: string): { baseName: string; number: string; isTrue: boolean } | null {
  const match = name.match(/^(.+?)_(\d{3})_(T|F)$/);
  if (!match) return null;
  return {
    baseName: match[1],
    number: match[2],
    isTrue: match[3] === 'T',
  };
}

// 收集所有 case
function collectCases(): Map<string, CaseInfo[]> {
  // key: 叶子目录相对路径, value: 该目录下的 case 列表
  const leafDirCases = new Map<string, CaseInfo[]>();

  function walkDir(dirPath: string): void {
    const entries = fs.readdirSync(dirPath, { withFileTypes: true });
    const phpFiles = entries.filter(e => e.isFile() && e.name.endsWith('.php'));
    const subDirs = entries.filter(e => e.isDirectory());

    // cross_file_package_namespace 特殊处理：子目录本身就是 case
    const relPath = path.relative(CASE_DIR, dirPath);
    const isCrossFile = relPath.includes('cross_file_package_namespace');

    if (isCrossFile && subDirs.length > 0) {
      // 每个子目录是一个 case，但它们的叶子目录是 cross_file_package_namespace
      const cases: CaseInfo[] = [];
      for (const subDir of subDirs) {
        const subDirPath = path.join(dirPath, subDir.name);
        const mainPhp = path.join(subDirPath, 'main.php');
        if (!fs.existsSync(mainPhp)) continue;

        const parsed = parseFileName(subDir.name);
        if (!parsed) continue;

        const annotations = parsePhpAnnotations(mainPhp);
        if (!annotations) continue;

        cases.push({
          fileName: subDir.name,
          filePath: mainPhp,
          ...annotations,
          isTrue: parsed.isTrue,
          baseName: parsed.baseName,
          number: parsed.number,
          leafDir: relPath,
        });
      }
      if (cases.length > 0) {
        leafDirCases.set(relPath, cases);
      }
      return;
    }

    if (phpFiles.length > 0) {
      // 普通叶子目录
      const cases: CaseInfo[] = [];
      for (const phpFile of phpFiles) {
        const nameWithoutExt = phpFile.name.replace('.php', '');
        const parsed = parseFileName(nameWithoutExt);
        if (!parsed) {
          console.warn(`  警告: 无法解析文件名 ${phpFile.name}`);
          continue;
        }

        const filePath = path.join(dirPath, phpFile.name);
        const annotations = parsePhpAnnotations(filePath);
        if (!annotations) continue;

        cases.push({
          fileName: nameWithoutExt,
          filePath,
          ...annotations,
          isTrue: parsed.isTrue,
          baseName: parsed.baseName,
          number: parsed.number,
          leafDir: relPath,
        });
      }
      if (cases.length > 0) {
        leafDirCases.set(relPath, cases);
      }
    }

    // 递归非 cross_file 的子目录
    if (!isCrossFile) {
      for (const subDir of subDirs) {
        walkDir(path.join(dirPath, subDir.name));
      }
    }
  }

  walkDir(CASE_DIR);
  return leafDirCases;
}

interface SceneEntry {
  compose: string;
  scene: string;
}

interface SceneLevel {
  level: string;
  scene_list: SceneEntry[];
}

interface ConfigEntry {
  evaluation_item: string;
  scene_levels: SceneLevel[];
}

// 生成 compose 字符串
function makeCompose(tCase: CaseInfo, fCase: CaseInfo | null, isCrossFile: boolean): string {
  if (isCrossFile) {
    // 跨文件 case 用子目录名
    const tPart = `${tCase.fileName}/main.php`;
    if (fCase) {
      const fPart = `${fCase.fileName}/main.php`;
      return `${tPart} && !${fPart}`;
    }
    return tPart;
  }
  // 普通 case 用文件名
  const tPart = `${tCase.fileName}.php`;
  if (fCase) {
    const fPart = `${fCase.fileName}.php`;
    return `${tPart} && !${fPart}`;
  }
  return tPart;
}

// 为一个叶子目录生成 config.json 内容
function generateConfig(leafDir: string, cases: CaseInfo[]): Record<string, ConfigEntry[]> {
  const dirName = path.basename(leafDir);
  const isCrossFile = leafDir.includes('cross_file_package_namespace');

  // 分离 T 和 F case
  const tCases = cases.filter(c => c.isTrue).sort((a, b) => {
    if (a.baseName !== b.baseName) return a.baseName.localeCompare(b.baseName);
    return a.number.localeCompare(b.number);
  });
  const fCases = cases.filter(c => !c.isTrue).sort((a, b) => {
    if (a.baseName !== b.baseName) return a.baseName.localeCompare(b.baseName);
    return a.number.localeCompare(b.number);
  });

  // 配对 T 和 F
  const pairs: Array<{ t: CaseInfo; f: CaseInfo | null }> = [];

  // 判断配对模式
  const isAccuracyConsecutive = leafDir.startsWith('accuracy/');

  if (isAccuracyConsecutive) {
    // 连续编号模式：001_T ↔ 002_F, 003_T ↔ 004_F
    // 在 accuracy 下，所有文件同 baseName，T 编号是奇数，F 编号是偶数
    for (const t of tCases) {
      const tNum = parseInt(t.number, 10);
      const fNum = String(tNum + 1).padStart(3, '0');
      const f = fCases.find(fc => fc.baseName === t.baseName && fc.number === fNum);
      pairs.push({ t, f: f || null });
    }
  } else {
    // 同编号模式优先：同 baseName + 同 number
    const usedF = new Set<string>();
    for (const t of tCases) {
      // 优先：同 baseName + 同编号
      let f = fCases.find(fc => !usedF.has(fc.fileName) && fc.baseName === t.baseName && fc.number === t.number);
      if (!f) {
        // 次选：同编号（不同 baseName）
        f = fCases.find(fc => !usedF.has(fc.fileName) && fc.number === t.number) || null;
      }
      if (f) {
        usedF.add(f.fileName);
      }
      pairs.push({ t, f: f || null });
    }
  }

  // 按 level 分组
  const levelMap = new Map<string, SceneEntry[]>();
  for (const pair of pairs) {
    const level = pair.t.level;
    if (!levelMap.has(level)) {
      levelMap.set(level, []);
    }
    const compose = makeCompose(pair.t, pair.f, isCrossFile);
    levelMap.get(level)!.push({
      compose,
      scene: pair.t.sceneIntroduction,
    });
  }

  // 构造 scene_levels
  const sceneLevels: SceneLevel[] = [];
  const sortedLevels = [...levelMap.keys()].sort((a, b) => parseInt(a) - parseInt(b));
  for (const level of sortedLevels) {
    sceneLevels.push({
      level,
      scene_list: levelMap.get(level)!,
    });
  }

  // 取第一个 case 的 evaluation_item（同目录下应一致）
  const evaluationItem = cases[0].evaluationItem;

  const config: Record<string, ConfigEntry[]> = {
    [dirName]: [
      {
        evaluation_item: evaluationItem,
        scene_levels: sceneLevels,
      },
    ],
  };

  return config;
}

// 主流程
function main(): void {
  console.log('开始解析 PHP benchmark case 并生成 config.json...\n');

  const leafDirCases = collectCases();

  let totalConfigs = 0;
  let totalCases = 0;
  const errors: string[] = [];

  for (const [leafDir, cases] of leafDirCases) {
    const config = generateConfig(leafDir, cases);
    const configPath = path.join(CASE_DIR, leafDir, 'config.json');
    const jsonStr = JSON.stringify(config, null, 2) + '\n';

    // 验证 JSON 可解析
    try {
      JSON.parse(jsonStr);
    } catch (e) {
      errors.push(`${configPath}: JSON 格式错误`);
      continue;
    }

    fs.writeFileSync(configPath, jsonStr, 'utf-8');
    totalConfigs++;
    totalCases += cases.length;
    console.log(`  生成: ${leafDir}/config.json (${cases.length} cases)`);
  }

  console.log(`\n=== 统计 ===`);
  console.log(`生成 config.json: ${totalConfigs} 个`);
  console.log(`覆盖 case: ${totalCases} 个`);

  if (errors.length > 0) {
    console.log(`\n=== 错误 ===`);
    for (const err of errors) {
      console.log(`  ${err}`);
    }
  }

  // 验证
  console.log('\n=== 验证 ===');
  let allValid = true;

  for (const [leafDir, cases] of leafDirCases) {
    const configPath = path.join(CASE_DIR, leafDir, 'config.json');
    if (!fs.existsSync(configPath)) {
      console.log(`  缺失: ${leafDir}/config.json`);
      allValid = false;
      continue;
    }

    const content = JSON.parse(fs.readFileSync(configPath, 'utf-8'));
    const dirName = path.basename(leafDir);
    const entries = content[dirName] as ConfigEntry[];
    if (!entries || entries.length === 0) {
      console.log(`  格式错误: ${leafDir}/config.json — 缺少 ${dirName} 字段`);
      allValid = false;
      continue;
    }

    // 验证 compose 中引用的文件存在
    const isCrossFile = leafDir.includes('cross_file_package_namespace');
    for (const entry of entries) {
      for (const sl of entry.scene_levels) {
        for (const scene of sl.scene_list) {
          const parts = scene.compose.split(/\s*&&\s*/);
          for (const part of parts) {
            const ref = part.replace(/^!/, '').trim();
            const refPath = path.join(CASE_DIR, leafDir, ref);
            if (!fs.existsSync(refPath)) {
              console.log(`  引用不存在: ${leafDir}/config.json -> ${ref}`);
              allValid = false;
            }
          }
        }
      }
    }
  }

  if (allValid) {
    console.log('  所有 config.json 验证通过');
  }

  // 同步到 yasa2 测试目录
  const yasa2PhpDir = path.join(__dirname, '..', '..', 'yasa2', 'test', 'php', 'benchmarks', 'sast-php', 'case');
  if (fs.existsSync(yasa2PhpDir)) {
    console.log('\n=== 同步到 yasa2 测试目录 ===');
    let syncCount = 0;
    for (const [leafDir] of leafDirCases) {
      const srcConfig = path.join(CASE_DIR, leafDir, 'config.json');
      const dstDir = path.join(yasa2PhpDir, leafDir);
      if (fs.existsSync(dstDir)) {
        const dstConfig = path.join(dstDir, 'config.json');
        fs.copyFileSync(srcConfig, dstConfig);
        syncCount++;
      } else {
        console.log(`  目标目录不存在，跳过: ${leafDir}`);
      }
    }
    console.log(`  同步: ${syncCount} 个 config.json`);
  } else {
    console.log(`\n注意: yasa2 测试目录不存在 (${yasa2PhpDir})，跳过同步`);
  }
}

main();
