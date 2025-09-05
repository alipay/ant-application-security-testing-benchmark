// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
// scene introduction = Promise
// level = 2+
// bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/asynchronous_promise_010_F
// evaluation information end

function asynchronous_promise_010_F(__taint_src) {
  let promise = new Promise((resolve, reject) => {
    resolve(__taint_src);
  });

  promise
    .then((result) => result + "_1")
    .then((result) => "_")
    .then((result) => {
      __taint_sink(result);
    });
}

function __taint_sink(o) {}
