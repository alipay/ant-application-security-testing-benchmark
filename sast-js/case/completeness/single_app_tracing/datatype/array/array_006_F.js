// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// scene introduction = push操作
// level = 2
// bind_url = completeness/single_app_tracing/datatype/array/array_006_F
// evaluation information end

function array_006_F(__taint_src) {
  let s = [];
  s.push("a");
  __taint_sink(s);
}
