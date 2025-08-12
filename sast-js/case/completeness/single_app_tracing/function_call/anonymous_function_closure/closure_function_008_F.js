// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
// scene introduction = 闭包->三阶定义在函数内
// level = 2
// bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/closure_function_008_F
// evaluation information end
const { execSync } = require('child_process');

function closure_function_008_F(__taint_src) {
  function outer() {
    let outerVar = "_";

    return function middle() {
      let middleVar = outerVar;

      return function inner() {
        __taint_sink(middleVar);
      };
    };
  }

  let middleFunction = outer();
  let innerFunction = middleFunction();
  innerFunction();
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

closure_function_008_F(taint_src);
