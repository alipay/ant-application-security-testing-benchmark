// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态
// scene introduction = 区分switch准入条件（不需求解）>switch->区分具体执行路径->不求解
// level = 3
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/no_solver/conditional_switch_006_T
// evaluation information end

function conditional_switch_006_T(__taint_src) {
  let data = "";
  switch (2) {
    case 2:
      data = __taint_src;
      break;
  }
  __taint_sink(data);
}

function __taint_sink(o) { }
