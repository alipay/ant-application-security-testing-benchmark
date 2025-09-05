// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->条件语句
// scene introduction = else
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/conditional_if_004_F
// evaluation information end

function conditional_if_004_F(__taint_src) {
  res = "";
  if (false) {
  } else {
    __taint_sink(res);
  }
}

function __taint_sink(o) {}
