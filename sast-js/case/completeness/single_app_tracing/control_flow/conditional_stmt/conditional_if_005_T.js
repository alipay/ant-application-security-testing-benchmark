// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->条件语句
// scene introduction = elseif
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/conditional_if_005_T
// evaluation information end

function conditional_if_005_T(__taint_src) {
  if (false) {
  } else if (true) {
    __taint_sink(__taint_src);
  } else {
  }
}

function __taint_sink(o) {}
