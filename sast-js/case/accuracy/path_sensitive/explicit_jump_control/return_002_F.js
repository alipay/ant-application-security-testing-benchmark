// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感分析->跳转语句
// scene introduction = return
// level = 4+
// bind_url = accuracy/path_sensitive/explicit_jump_control/return_002_F
// evaluation information end
const { execSync } = require('child_process');


function return_002_F(__taint_src) {
  process(__taint_src, "some_condition");

  function process(arg1, arg2) {
    if (arg2 === "some_condition") {
       arg1 = "_";
      __taint_sink(arg1);
      return;
    }
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

return_002_F(taint_src);
