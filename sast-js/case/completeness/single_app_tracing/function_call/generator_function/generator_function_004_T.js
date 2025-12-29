// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->生成器函数
// scene introduction = 二阶
// level = 2
// bind_url = completeness/single_app_tracing/function_call/generator_function/generator_function_004_T
// evaluation information end
const { execSync } = require('child_process');


function generator_function_004_T(__taint_src) {
  function* myGen() {
    const a = yield __taint_src;
    const b = yield a + 2;
    yield b + "_";
  }

  const g = myGen();
  g.next().value;
  g.next(1).value;
  __taint_sink(g.next(__taint_src).value);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

generator_function_004_T(taint_src);
