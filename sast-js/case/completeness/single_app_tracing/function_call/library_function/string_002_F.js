// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->库函数调用
// scene introduction = 字符串库
// level = 2
// bind_url = completeness/single_app_tracing/function_call/library_function/string_002_F
// evaluation information end
const { execSync } = require('child_process');

function string_002_F(__taint_src) {
  process("_");

  function process(arg) {
    let result = arg.trim();
    __taint_sink(result);
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

string_002_F(taint_src);
