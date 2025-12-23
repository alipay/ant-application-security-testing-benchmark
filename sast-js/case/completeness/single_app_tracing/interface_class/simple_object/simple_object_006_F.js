// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->接口与类->简单对象
// scene introduction = 字面量
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/simple_object/simple_object_006_F
// evaluation information end
const { execSync } = require("child_process");

function simple_object_006_F(__taint_src) {
  let person = {
    name: __taint_src,
  };
  person.name = "safe_value";
  __taint_sink(person.name);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

simple_object_006_F(taint_src);
