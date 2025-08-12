// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象的不同字段
// scene introduction = 有参构造函数
// level = 3
// bind_url = accuracy/field_sensitive/class/constructor_field_003_F
// evaluation information end
const { execSync } = require('child_process');


function constructor_field_003_F(__taint_src) {
  class A {
    constructor(param) {
      this.data = param;
      this.sani = "_";
    }
  }

  let obj = new A(__taint_src);
  __taint_sink(obj.sani);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

constructor_field_003_F(taint_src);
