// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
// scene introduction = dowhile
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/dowhile_body_002_F
// evaluation information end

function dowhile_body_002_F(__taint_src) {
  let res = "";
  do {
    res = "";
  } while (__taint_sink(res));
}

function __taint_sink(o) {}
