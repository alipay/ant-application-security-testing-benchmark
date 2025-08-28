// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->跳转语句
// scene introduction = continue
// level = 4+
// bind_url = accuracy/path_sensitive/explicit_jump_control/continue_001_T
// evaluation information end
const { execSync } = require('child_process');


function continue_001_T(__taint_src) {
  let res = "abc";
  for (let i = 0; i < 10; i++) {
    if (i === 3) {
      res = __taint_src;
      continue;
    }
    __taint_sink(res);
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

continue_001_T(taint_src);
