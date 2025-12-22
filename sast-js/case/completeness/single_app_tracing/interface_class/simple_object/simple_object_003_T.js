// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->简单对象
// scene introduction = 构造函数创建
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/simple_object/simple_object_003_T
// evaluation information end
const { execSync } = require("child_process");

function simple_object_003_T(__taint_src) {
  function Person() {}

  Person.prototype.name = __taint_src;

  let person = new Person();

  __taint_sink(person.name);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

simple_object_003_T(taint_src);
