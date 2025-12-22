// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->标签函数
// scene introduction =
// level = 4
// bind_url = completeness/single_app_tracing/function_call/tagged_template_literals/tagged_template_literal_002_T
// evaluation information end
const { execSync } = require('child_process');


function tagged_template_literal_002_T(__taint_src) {
  function myTag(strings, ...values) {
    __taint_sink(values);
  }

  const extra = "_";
  myTag`Hello ${__taint_src} world ${extra}`;
}

function __taint_sink(o) {
  execSync(JSON.stringify(o));
}

const taint_src = "taint_src_value";

tagged_template_literal_002_T(taint_src);
