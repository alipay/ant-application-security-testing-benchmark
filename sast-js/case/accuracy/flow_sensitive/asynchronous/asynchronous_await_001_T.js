// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感分析->异步执行
// scene introduction = 异步执行-await
// level = 4
// bind_url = accuracy/flow_sensitive/asynchronous/asynchronous_await_001_T
// evaluation information end
const { execSync } = require('child_process');


async function asynchronous_await_001_T(__taint_src) {
  let data = "";
  data = await process();
  __taint_sink(data);

  async function process() {
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        resolve(__taint_src);
      }, 10);
    });
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

asynchronous_await_001_T(taint_src);
