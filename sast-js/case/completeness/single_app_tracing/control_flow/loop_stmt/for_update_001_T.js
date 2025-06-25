// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
// scene introduction = for_update
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/for_update_001_T
// evaluation information end

function for_update_001_T(__taint_src) {
  let res = "";
  let ini = 0;
  let j = "_";
  for (; ini < 2; j = __taint_src) {
    res = j;
    ini++;
  }
  __taint_sink(res);
}

function __taint_sink(o) {}
