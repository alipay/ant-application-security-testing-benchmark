// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感分析->跳转语句
// scene introduction = return
// level = 4+
// bind_url = accuracy/path_sensitive/explicit_jump_control/return_002_F
// evaluation information end

function return_002_F(__taint_src) {
  process(__taint_src, "some_condition");

  function process(arg1, arg2) {
    if (arg2 === "some_condition") {
      return;
    }
    __taint_sink(arg1);
  }
}

function __taint_sink(o) { }
