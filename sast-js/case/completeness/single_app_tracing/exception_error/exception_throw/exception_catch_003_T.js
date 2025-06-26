// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->异常与错误处理->异常抛出与捕获
// scene introduction = exception_catch
// level = 2+
// bind_url = completeness/chain_tracing/exception_error/exception_throw/exception_catch_003_T
// evaluation information end

function exception_catch_003_T(__taint_src) {
  try {
    throw { message: __taint_src, code: 123 };
  } catch (e) {
    __taint_sink(e.message);
  }
}

function __taint_sink(o) { }
