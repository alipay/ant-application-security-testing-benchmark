// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感分析->多态->能够对上下文条件进行求解，以区分不同执行路径的状态
// scene introduction = 条件表达式（需求解）
// level = 4
// bind_url = accuracy/context_sensitive/polymorphism/polymorphism_003_T
// evaluation information end
const { execSync } = require('child_process');

function polymorphism_003_T(__taint_src) {
  class BaseClass1 {
    constructor() {
      this.data = __taint_src;
    }

    getData() {
      return this.data;
    }
  }

  class BaseClass2 {
    constructor() {
      this.data = "_";
    }

    getData() {
      return this.data;
    }
  }

  class SubClass extends (1 + 1 === 2 ? BaseClass1 : BaseClass2) {
    constructor() {
      super();
    }

    describe() {
      return this.getData();
    }
  }

  const subClass = new SubClass();

  __taint_sink(subClass.describe());
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

polymorphism_003_T(taint_src);
