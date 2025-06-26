// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态
// scene introduction = 区分switch准入条件（不需求解）->switch->区分分支
// level = 3
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/no_solver/conditional_switch_004_F
// evaluation information end

function conditional_switch_004_F(__taint_src) {
  let data = "";
  switch (2) {
    case 1:
      __taint_sink(data);
      break;
    default:
      data = __taint_src;
  }
}

function __taint_sink(o) { }
