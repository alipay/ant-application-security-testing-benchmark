// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
// scene introduction = for in-body
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/forin_body_002_F
// evaluation information end

function forin_body_002_F(__taint_src) {
  let obj = { key1: "value1", key2: "value2" };
  let res = "";

  for (let key in obj) {
    res += obj[key];
  }

  __taint_sink(res);
}

function __taint_sink(o) {}
