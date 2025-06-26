// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
// scene introduction =  some
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/some_001_T
// evaluation information end

function some_001_T(__taint_src) {
  let arr = ["a", "b", __taint_src];
  arr.some(function (item) {
    __taint_sink(item);
  });
}

function __taint_sink(o) {}
