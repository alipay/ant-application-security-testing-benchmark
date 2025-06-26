// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->跳转语句
// scene introduction = break_label
// level = 4+
// bind_url = accuracy/path_sensitive/explicit_jump_control/break_label_002_T
// evaluation information end

function break_label_002_T(__taint_src) {
  let res = "";

  myLabel1: {
    for (let i = 0; i < 2; i++) {
      res = __taint_src;
      break myLabel1;
    }
  }
  myLabel2: {
    for (let i = 0; i < 2; i++) {
      break myLabel2;
      res = "_";
    }
  }

  __taint_sink(res);
}

function __taint_sink(o) { }
