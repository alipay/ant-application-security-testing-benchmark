// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
// scene introduction = symbol赋值
// level = 2
// bind_url = completeness/single_app_tracing/datatype/primitives/symbol_002_F
// date = 2025-12-16 09:00:33
// evaluation information end
const { execSync } = require('child_process');

function symbol_002_F(__taint_src) {
  // 场景特点：将污点源赋值后使用安全symbol覆盖
  let sym = __taint_src;
  sym = Symbol('safe_symbol');
  __taint_sink(sym.toString());
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = Symbol('taint_symbol');
symbol_002_F(taint_src);