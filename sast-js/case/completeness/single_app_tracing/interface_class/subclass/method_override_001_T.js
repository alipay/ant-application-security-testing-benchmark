// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->子类对象
// scene introduction = 子类覆盖
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/subclass/method_override_001_T
// date = 2025-12-17 08:20:50
// evaluation information end
const { execSync } = require("child_process");

function method_override_001_T(__taint_src) {
  class Parent {
    getData() {
      return "safe_data";
    }
  }

  class Child extends Parent {
    // 场景特点：子类重写父类方法并调用父类方法
    getData() {
      return __taint_src;
    }
  }

  const child = new Child();
  const result = child.getData();
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";
method_override_001_T(taint_src);
