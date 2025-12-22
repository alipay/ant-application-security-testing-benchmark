// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->接口与类->子类对象
// scene introduction = 父类属性
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/subclass/inheritance_parent_property_002_F
// date = 2025-12-17 08:20:50
// evaluation information end
const { execSync } = require('child_process');

function inheritance_parent_property_002_F(__taint_src) {
  class Parent {
    constructor(data) {
      // 场景特点：父类定义属性
      this.parentData = data;
    }
  }

  class Child extends Parent {
    constructor(data) {
      super(data);
    }

    getParentData() {
      // 场景特点：子类访问父类属性，但返回非污点数据
      return "safe_data";
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
inheritance_parent_property_002_F(taint_src);