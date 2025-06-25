// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->异常与错误处理->异常抛出与捕获
// scene introduction = exception_try
// level = 2+
// bind_url = completeness/chain_tracing/exception_error/exception_throw/exception_try_002_F
// evaluation information end

function exception_try_002_F(__taint_src) {
  try {
    __taint_sink("_");
    throw "_";
  } catch (e) { }
}

function __taint_sink(o) { }
