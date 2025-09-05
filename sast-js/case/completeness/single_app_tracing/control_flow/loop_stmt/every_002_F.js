// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
// scene introduction = every
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/every_002_F
// evaluation information end

function every_002_F(__taint_src) {
  let arr = ["a", "b", "c"];
  arr.every(function (item) {
    __taint_sink(item);
  });
}

function __taint_sink(o) {}
