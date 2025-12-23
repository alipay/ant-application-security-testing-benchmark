// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感->多次调用
// scene introduction = 链式调用
// level = 2
// bind_url = accuracy/context_sensitive/multi_invoke/chain_invoke_002_F
// date = 2025-12-19 06:13:05
// evaluation information end
const { execSync } = require("child_process");

function chain_invoke_002_F(__taint_src) {
  let obj = {
    data: "",
    process(arg) {
      this.data = arg;
      return this;
    },
  };
  // 场景特点：链式调用中污染源未到达最终sink点
  obj.process(__taint_src).process("safe_value");
  __taint_sink(obj.data);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

chain_invoke_002_F(taint_src);
