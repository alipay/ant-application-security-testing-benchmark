// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
// scene introduction = Promise错误处理
// level = 2
// bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/asynchronous_promise_012_F
// evaluation information end

function asynchronous_promise_012_F(__taint_src) {
  let promise = new Promise((resolve, reject) => {
    reject(__taint_src);
  });

  promise
    .then((result) => {
      __taint_sink(result);
    })
    .catch((error) => {});
}

function __taint_sink(o) {}
