// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
// scene introduction = 字面量声明
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/complex_object/cross_class_005_T
// date = 2025-12-17 08:20:50
// evaluation information end
const { execSync } = require("child_process");

function cross_class_005_T(__taint_src) {
  const a = {
    b: {
      c: __taint_src,
    },
  };
  __taint_sink(a.b.c);
}

function __taint_sink(data) {
  execSync(JSON.stringify(data));
}

const taint_src = "taint_src_value";

cross_class_005_T(taint_src);
