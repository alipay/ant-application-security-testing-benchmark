// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
// scene introduction = for of-body
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/forof_body_001_T
// evaluation information end

function forof_body_001_T(__taint_src) {
  let arr = ["a", "b", __taint_src];
  let res = "";
  for (let value of arr) {
    res = value;
  }
  __taint_sink(res);
}

function __taint_sink(o) {}
