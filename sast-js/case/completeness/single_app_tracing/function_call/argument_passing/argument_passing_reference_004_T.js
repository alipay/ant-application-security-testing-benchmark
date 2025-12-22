// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = 引用传递数组
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_reference_004_T
// evaluation information end
const { execSync } = require('child_process');


function argument_passing_reference_004_T(__taint_src) {
  let arr = ["_"];
  process(arr);
  __taint_sink(arr);

  function process(inputArr) {
    inputArr[0] = __taint_src;
  }
}

function __taint_sink(o) {
  execSync(JSON.stringify(o));
}

const taint_src = "taint_src_value";

argument_passing_reference_004_T(taint_src);
