// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// scene introduction = 普通
// level = 2
// bind_url = completeness/single_app_tracing/datatype/array/array_002_F
// evaluation information end

function array_002_F(__taint_src) {
  let s2 = ["a", "b", "c"];
  __taint_sink(s2);
}

function __taint_sink(o) { }
