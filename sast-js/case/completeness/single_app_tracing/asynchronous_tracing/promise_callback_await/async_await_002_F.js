// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
// scene introduction = async/await
// level = 2
// bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/async_await_002_F
// evaluation information end
const { execSync } = require('child_process');


async function async_await_002_F(__taint_src) {
  let data = await process();
  __taint_sink(data);

  async function process() {
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        resolve("aa");
      }, 10);
    });
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

async_await_002_F(taint_src);
