// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = 值传递嵌套函数
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_value_005_F
// evaluation information end

function argument_passing_value_005_F(__taint_src) {
  function outer(input) {
    function inner(innerInput) {
      __taint_sink(innerInput);
    }

    inner(input);
  }

  outer("_");
}

function __taint_sink(o) { }
