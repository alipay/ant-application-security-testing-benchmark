// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->变量作用域->private变量
// scene introduction = 私有变量
// level = 2
// bind_url = completeness/single_app_tracing/variable_scope/private_variable/private_variable_002_F
// evaluation information end
const { execSync } = require('child_process');


function private_variable_002_F(__taint_src) {
  class A {
    #data = __taint_src;
    GetData() {
      return this.#data;
    }
    SetData(data) {
      this.#data = data;
    }
  }
  let o = new A();
  o.SetData("_");
  __taint_sink(o.GetData());
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

private_variable_002_F(taint_src);
