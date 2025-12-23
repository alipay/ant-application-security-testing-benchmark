// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
// scene introduction = bigint赋值
// level = 2
// bind_url = completeness/single_app_tracing/datatype/primitives/bigint_001_T
// date = 2025-12-16 09:00:33
// evaluation information end
const { execSync } = require('child_process');

function bigint_001_T(__taint_src) {
  // 场景特点：将污点源直接赋值给bigint变量
  let big = __taint_src;
  __taint_sink(big.toString());
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = 12345678901234567890n;
bigint_001_T(taint_src);