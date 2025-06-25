// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
// scene introduction = dowhile
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/dowhile_body_001_T
// evaluation information end

function dowhile_body_001_T(__taint_src) {
  let res = "";
  do {
    res = res + __taint_src;
  } while (__taint_sink(res));
}

function __taint_sink(o) {}
