// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->生成器函数
// scene introduction = 一阶
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/generator_function/generator_function_001_F
// evaluation information end
const { execSync } = require('child_process');


function generator_function_001_F(__taint_src) {
  function* myGen() {
    const a = yield 2;
    const b = yield a + 2;
    yield b + 3;
  }

  const g = myGen();
  __taint_sink(g.next().value);
}

function __taint_sink(o) {
  execSync(String(o));
}

const taint_src = "taint_src_value";

generator_function_001_F(taint_src);
