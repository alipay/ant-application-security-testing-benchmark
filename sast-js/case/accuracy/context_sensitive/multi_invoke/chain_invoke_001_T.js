// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感->多次调用
// scene introduction = 链式调用
// level = 2
// bind_url = accuracy/context_sensitive/multi_invoke/chain_invoke_001_T
// date = 2025-12-19 06:13:00
// evaluation information end
const { execSync } = require("child_process");

function chain_invoke_001_T(__taint_src) {
  let obj = {
    data: "",
    process(arg) {
      this.data = arg;
      return this;
    },
  };
  // 场景特点：函数结果作为下次调用参数，形成链式调用传递污染源
  obj.process("safe_value").process(__taint_src);
  __taint_sink(obj.data);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

chain_invoke_001_T(taint_src);
