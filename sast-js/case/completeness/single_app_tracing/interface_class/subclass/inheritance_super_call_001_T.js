// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->子类对象
// scene introduction = super调用
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/subclass/inheritance_super_call_001_T
// date = 2025-12-17 08:20:50
// evaluation information end
const { execSync } = require('child_process');

function inheritance_super_call_001_T(__taint_src) {
  class Parent {
    constructor(data) {
      this.data = data;
    }

    getData() {
      return this.data;
    }
  }

  class Child extends Parent {
    constructor(data) {
      super(data);
    }

    getParentData() {
      // 场景特点：子类通过super调用父类方法
      return super.getData();
    }
  }

  const child = new Child(__taint_src);
  const result = child.getParentData();
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";
inheritance_super_call_001_T(taint_src);