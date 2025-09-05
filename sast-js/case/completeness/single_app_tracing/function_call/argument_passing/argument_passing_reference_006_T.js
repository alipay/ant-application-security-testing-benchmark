// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = 引用传递map
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_reference_006_T
// evaluation information end

function argument_passing_reference_006_T(__taint_src) {
  const objA = { name: __taint_src };
  const objB = { name: "Bob" };

  swap(objA, objB);
  __taint_sink(objB.name);

  function swap(obj1, obj2) {
    const temp = obj1.name;
    obj1.name = obj2.name;
    obj2.name = temp;
  }
}

function __taint_sink(o) { }
