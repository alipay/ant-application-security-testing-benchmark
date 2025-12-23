// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感分析->异常抛出和捕获
// scene introduction = 异常抛出- catch块
// level = 3
// bind_url = accuracy/path_sensitive/exception_throw/exception_throw_004_F
// evaluation information end
const { execSync } = require("child_process");

function exception_throw_004_F(__taint_src) {
  try {
    throw "safe_value";
  } catch (e) {
    __taint_sink(e);
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

exception_throw_004_F(taint_src);
