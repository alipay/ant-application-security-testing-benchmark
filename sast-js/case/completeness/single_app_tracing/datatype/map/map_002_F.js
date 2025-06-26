// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字典
// scene introduction = 字典/映射（Map）对象
// level = 2
// bind_url = completeness/single_app_tracing/datatype/map/map_002_F
// evaluation information end

function map_002_F(__taint_src) {
  let m = new Map();
  m.set("key1", "_");
  __taint_sink(m);
}

function __taint_sink(o) { }
