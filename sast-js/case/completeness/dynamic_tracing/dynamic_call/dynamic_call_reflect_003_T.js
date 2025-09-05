// evaluation information start
// real case = true
// evaluation item = 完整度->动态特性跟踪完整度->反射调用
// scene introduction = 反射
// level = 3
// bind_url = completeness/dynamic_tracing/dynamic_call/dynamic_call_reflect_003_T
// evaluation information end

function dynamic_call_reflect_003_T(__taint_src) {
  let obj = {
    data: "_",
  };

  Reflect.set(obj, "foo", __taint_src);
  __taint_sink(obj.foo);
}

function __taint_sink(o) {}
