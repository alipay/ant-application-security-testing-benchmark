// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
// scene introduction = Promise
// level = 2+
// bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/asynchronous_promise_007_T
// evaluation information end

function asynchronous_promise_007_T(__taint_src) {
  let promise = new Promise((resolve, reject) => {
    resolve(__taint_src);
  });
  let final;
  promise
    .then((result) => {
      final = result;
    })
    .catch((error) => {
      console.error(error);
    })
    .finally(() => {
      __taint_sink(final);
    });
}

function __taint_sink(o) {}
