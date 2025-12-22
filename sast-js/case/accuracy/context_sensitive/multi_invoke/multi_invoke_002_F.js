// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感->多次调用
// scene introduction = 普通
// level = 2
// bind_url = accuracy/context_sensitive/multi_invoke/multi_invoke_002_F
// evaluation information end
const { execSync } = require('child_process');

function multi_invoke_002_F(__taint_src) {
  let a = process(__taint_src);
  let b = process("_");
  __taint_sink(b);

  function process(arg) {
    return arg;
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

multi_invoke_002_F(taint_src);
