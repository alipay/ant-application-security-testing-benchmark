// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->异常与错误处理->异常抛出与捕获
// scene introduction = exception_catch
// level = 2+
// bind_url = completeness/chain_tracing/exception_error/exception_throw/exception_catch_002_F
// evaluation information end

function exception_catch_002_F(__taint_src) {
  res = "";
  try {
    throw __taint_src;
  } catch (e) {
    __taint_sink(res);
  }
}

function __taint_sink(o) { }
