// evaluation information start
// real case = false
// evaluation item = 完整度->动态特性跟踪完整度->反射调用
// scene introduction = 反射
// level = 3
// bind_url = completeness/dynamic_tracing/dynamic_call/dynamic_call_reflect_004_F
// evaluation information end

function dynamic_call_reflect_004_F(__taint_src) {
  let obj = {
    data: __taint_src,
  };

  Reflect.set(obj, "foo", "_");
  __taint_sink(obj.foo);
}

function __taint_sink(o) {}
