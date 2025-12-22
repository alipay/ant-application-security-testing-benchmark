// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同的类对象、结构体/联合体和字典/列表/数组
// scene introduction = 接口/类->类对象
// level = 2
// bind_url = accuracy/object_field_sensitive/object_sensitive/constructor_obj_002_F
// evaluation information end
const { execSync } = require('child_process');


function constructor_obj_002_F(__taint_src) {
  class A {
    constructor(data) {
      this.data = data;
    }
  }

  let obj = new A(__taint_src);
  let o = new A("_");
  __taint_sink(o);
}

function __taint_sink(o) {
   execSync(JSON.stringify(o));
}

const taint_src = "taint_src_value";

constructor_obj_002_F(taint_src);
