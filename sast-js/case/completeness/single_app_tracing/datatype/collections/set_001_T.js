// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->集合
// scene introduction = 集合（Set）对象
// level = 2
// bind_url = completeness/single_app_tracing/datatype/collections/set_001_T
// evaluation information end

function set_001_T(__taint_src) {
  let set = new Set(__taint_src);
  __taint_sink(set);
}

function __taint_sink(o) { }
