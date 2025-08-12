// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->方法重写
// scene introduction = 构造函数->原型链
// level = 2
// bind_url = completeness/single_app_tracing/function_call/override/prototype_extends_002_F
// evaluation information end
const { execSync } = require('child_process');

function prototype_extends_002_F(__taint_src) {
  function Father() {
    this.property = "_";
  }
  Father.prototype.getFaValue = function () {
    return this.property;
  };
  function Child() {
    this.chProperty = __taint_src;
  }

  Child.prototype = new Father();
  Child.prototype.getChValue = function () {
    return this.chProperty;
  };

  let instance = new Child();
  __taint_sink(instance.getFaValue());
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

prototype_extends_002_F(taint_src);
