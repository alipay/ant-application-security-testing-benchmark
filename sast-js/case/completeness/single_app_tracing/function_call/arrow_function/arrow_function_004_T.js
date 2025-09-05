// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->箭头函数
// scene introduction = 二阶
// level = 2
// bind_url = completeness/single_app_tracing/function_call/arrow_function/arrow_function_004_T
// evaluation information end

function arrow_function_004_T(__taint_src) {
  let outer = () => {
    return () => {
      __taint_sink(__taint_src);
    };
  };

  let innerFunction = outer();
  innerFunction();
}

function __taint_sink(o) { }
