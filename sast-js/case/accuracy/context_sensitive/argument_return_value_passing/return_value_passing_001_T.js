// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 返回值传递
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/return_value_passing_001_T
// evaluation information end
function return_value_passing_001_T(__taint_src) {
  let data = process();
  __taint_sink(data);

  function process() {
    return __taint_src;
  }
}

function __taint_sink(o) { }
