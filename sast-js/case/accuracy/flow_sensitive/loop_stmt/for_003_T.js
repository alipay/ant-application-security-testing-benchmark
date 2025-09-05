// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感分析->循环顺序执行语句
// scene introduction = for->init流敏感
// level = 2
// bind_url = accuracy/flow_sensitive/loop_stmt/for_003_T
// evaluation information end

function for_003_T(__taint_src) {
  let res = "";
  let i = 0;
  for (res = __taint_src; i < 1; i++) {
    __taint_sink(res);
  }
}

function __taint_sink(o) {}
