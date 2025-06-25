// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感分析->循环顺序执行语句
// scene introduction = for->body流敏感
// level = 2
// bind_url = accuracy/flow_sensitive/loop_stmt/for_001_T
// evaluation information end

function for_001_T(__taint_src) {
  let res = "";

  for (let i = 0; i < 1; i++) {
    res = __taint_src;
    __taint_sink(res);
  }
}

function __taint_sink(o) { }
