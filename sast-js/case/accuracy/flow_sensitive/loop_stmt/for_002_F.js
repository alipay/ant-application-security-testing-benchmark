// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感分析->循环顺序执行语句
// scene introduction = for->body流敏感
// level = 2
// bind_url = accuracy/flow_sensitive/loop_stmt/for_002_F
// evaluation information end

function for_002_F(__taint_src) {
  let res = "";

  for (let i = 0; i < 1; i++) {
    __taint_sink(res);
    res = __taint_src;
  }
}

function __taint_sink(o) { }
