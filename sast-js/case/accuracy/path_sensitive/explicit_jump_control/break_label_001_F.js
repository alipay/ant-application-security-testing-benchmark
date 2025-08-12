// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感分析->跳转语句
// scene introduction = break_label
// level = 4+
// bind_url = accuracy/path_sensitive/explicit_jump_control/break_label_001_F
// evaluation information end
const { execSync } = require('child_process');


function break_label_001_F(__taint_src) {
  let res = "";

  myLabel1: {
    for (let i = 0; i < 2; i++) {
      res = __taint_src;
      break myLabel1;
    }
  }
  myLabel2: {
    for (let i = 0; i < 2; i++) {
      res = "_";
      break myLabel2;
    }
  }

  __taint_sink(res);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

break_label_001_F(taint_src);
