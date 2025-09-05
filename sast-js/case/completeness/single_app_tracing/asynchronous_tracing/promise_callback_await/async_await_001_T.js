// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
// scene introduction = async/await
// level = 2
// bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/async_await_001_T
// evaluation information end

async function async_await_001_T(__taint_src) {
  let data = await process();
  __taint_sink(data);

  async function process() {
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        resolve(__taint_src);
      }, 10);
    });
  }
}

function __taint_sink(o) { }
