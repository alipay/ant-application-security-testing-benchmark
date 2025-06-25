// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->简单对象
// scene introduction = 原型链
// level = 2
// bind_url = completeness/single_app_tracing/class/simple_object/simple_object_prototype_003_T
// evaluation information end

function simple_object_prototype_003_T(__taint_src) {
  function Person() {}

  Person.prototype.name = __taint_src;

  let person = new Person();

  __taint_sink(person.__proto__.name);
}

function __taint_sink(o) {}
