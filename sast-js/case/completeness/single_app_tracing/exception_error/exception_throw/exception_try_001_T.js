// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->异常与错误处理->异常抛出与捕获
// scene introduction = exception_try
// level = 2+
// bind_url = completeness/single_app_tracing/exception_error/exception_throw/exception_try_001_T
// evaluation information end
const { execSync } = require('child_process');


function exception_try_001_T(__taint_src) {
  try {
    __taint_sink(__taint_src);
    throw "_";
  } catch (e) { }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

exception_try_001_T(taint_src);
