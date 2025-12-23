// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
// scene introduction = 数字赋值
// level = 2
// bind_url = completeness/single_app_tracing/datatype/primitives/number_001_T
// date = 2025-12-16 09:00:33
// evaluation information end
const { execSync } = require('child_process');

function number_001_T(__taint_src) {
  // 场景特点：将污点源直接赋值给数字变量
  let num = __taint_src;
  __taint_sink(num);
}

function __taint_sink(o) {
  execSync(o.toString());
}

const taint_src = 12345;
number_001_T(taint_src);