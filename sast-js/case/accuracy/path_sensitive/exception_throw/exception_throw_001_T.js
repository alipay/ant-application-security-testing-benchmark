// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->异常抛出和捕获
// scene introduction = 异常抛出-try块
// level = 3
// bind_url = accuracy/path_sensitive/exception_throw/exception_throw_001_T
// evaluation information end
const { execSync } = require('child_process');


function exception_throw_001_T(__taint_src) {
  try {
    __taint_sink(__taint_src);
    throw "_";
  } catch (e) { }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

exception_throw_001_T(taint_src);
