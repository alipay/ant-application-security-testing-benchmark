// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->变量作用域->静态变量
// scene introduction = 普通
// level = 2
// bind_url = completeness/single_app_tracing/variable_scope/static_variable/static_variable_002_F
// evaluation information end

function static_variable_002_F(__taint_src) {
  class A {
    static data = "_";
  }

  __taint_sink(A.data);
}

function __taint_sink(o) { }
