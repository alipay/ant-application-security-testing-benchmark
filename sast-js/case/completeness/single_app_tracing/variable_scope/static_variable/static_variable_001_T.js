// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->变量作用域->静态变量
// scene introduction = 普通
// level = 2
// bind_url = completeness/single_app_tracing/variable_scope/static_variable/static_variable_001_T
// evaluation information end

function static_variable_001_T(__taint_src) {
  class A {
    static data = __taint_src;
  }

  __taint_sink(A.data);
}

function __taint_sink(o) { }
