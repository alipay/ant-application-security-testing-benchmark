// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感分析->异常抛出和捕获
// scene introduction = 异常抛出-try块
// level = 3
// bind_url = accuracy/path_sensitive/exception_throw/exception_throw_002_F
// evaluation information end

function exception_throw_002_F(__taint_src) {
  try {
    throw "_";
    __taint_sink(__taint_src);
  } catch (e) { }
}

function __taint_sink(o) { }
