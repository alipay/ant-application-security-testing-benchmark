// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->能够对上下文条件进行求解，以区分不同执行路径的状态
// scene introduction = 区分switch准入条件（需要求解）->switch->区分具体执行路径->求解
// level = 4
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/solver/conditional_switch_008_T
// evaluation information end

function conditional_switch_008_T(__taint_src) {
  let data = "";
  switch (1 + 1) {
    case 2:
      data = __taint_src;
      break;
  }
  __taint_sink(data);
}

function __taint_sink(o) { }
