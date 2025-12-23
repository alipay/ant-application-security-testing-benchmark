// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = this绑定参数->call
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_this_binding_002_T
// date = 2025-12-18 06:35:30
// evaluation information end
const { execSync } = require("child_process");

function argument_passing_this_binding_002_T(__taint_src) {
  const obj = {
    data: "safe_value",
  };
  const obj2 = {
    data: "safe_value",
    func(param) {
      this.data = param;
      __taint_sink(obj.data);
    },
  };

  // 场景特点：使用call方法绑定this并传递污点参数
  obj2.func.call(obj, __taint_src);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

argument_passing_this_binding_002_T(taint_src);
