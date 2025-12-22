// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
// scene introduction = 数字赋值
// level = 2
// bind_url = completeness/single_app_tracing/datatype/primitives/number_002_F
// date = 2025-12-16 09:00:33
// evaluation information end
const { execSync } = require('child_process');

function number_002_F(__taint_src) {
  // 场景特点：将污点源赋值后使用安全数字覆盖
  let num = __taint_src;
  num = 999;
  __taint_sink(num.toString());
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = 12345;
number_002_F(taint_src);