// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
// scene introduction = for_update
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/for_update_002_F
// evaluation information end

function for_update_002_F(__taint_src) {
  let res = "";
  let ini = 0;
  let j = "_";
  for (; ini < 2; j = res) {
    res = j;
    ini++;
  }
  __taint_sink(res);
}

function __taint_sink(o) {}
