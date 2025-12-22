// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 返回值传递
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/return_value_passing_002_F
// evaluation information end
const { execSync } = require('child_process');

function return_value_passing_002_F(__taint_src) {
  let data = process();
  __taint_sink(data);

  function process() {
    return "_";
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

return_value_passing_002_F(taint_src);
