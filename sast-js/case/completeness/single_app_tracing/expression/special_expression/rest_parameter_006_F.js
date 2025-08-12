// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = 扩展运算符
// level = 2
// bind_url = completeness/single_app_tracing/expression/special_expression/rest_parameter_006_F
// evaluation information end
const { execSync } = require('child_process');


function rest_parameter_006_F(__taint_src) {
  collectArgs(["prefix", __taint_src, "suffix"]);
}

function collectArgs(...args) {
  __taint_sink("aa");
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

rest_parameter_006_F(taint_src);
