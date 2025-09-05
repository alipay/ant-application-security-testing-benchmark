// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感->多次调用
// scene introduction = 普通
// level = 2
// bind_url = accuracy/context_sensitive/multi_invoke/multi_invoke_001_T
// evaluation information end
function argument_passing_value_001_T(__taint_src) {
  let a = process(__taint_src);
  let b = process("_");
  __taint_sink(a);

  function process(arg) {
    return arg;
  }
}

function __taint_sink(o) {}
