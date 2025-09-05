// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->类型转换
// scene introduction = 显式类型转换
// level = 2
// bind_url = completeness/single_app_tracing/expression/type_cast/type_cast_001_T
// evaluation information end

function type_cast_001_T(__taint_src) {
  let result = Number(__taint_src);
  __taint_sink(result);
}

function __taint_sink(o) { }
