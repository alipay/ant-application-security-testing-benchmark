// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感分析->异步执行
// scene introduction = 异步执行-await
// level = 4
// bind_url = accuracy/flow_sensitive/asynchronous/asynchronous_await_002_F
// evaluation information end

async function asynchronous_await_002_F(__taint_src) {
  let data = "";
  __taint_sink(data);
  data = await process();

  async function process() {
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        resolve(__taint_src);
      }, 10);
    });
  }
}

function __taint_sink(o) { }
