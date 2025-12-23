// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 参数值传递->参数位置
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/argument_passing_value_004_F
// evaluation information end
const { execSync } = require('child_process');


function argument_passing_value_004_F(__taint_src) {
  process(__taint_src, "_");
}

function process(arg1, arg2) {
  __taint_sink(arg2);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

argument_passing_value_004_F(taint_src);
