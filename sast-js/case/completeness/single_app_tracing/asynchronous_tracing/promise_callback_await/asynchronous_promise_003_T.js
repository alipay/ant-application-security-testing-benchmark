// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
// scene introduction = Promise
// level = 2+
// bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/asynchronous_promise_003_T
// evaluation information end

function asynchronous_promise_003_T(__taint_src) {
  function asyncFunc(value) {
    return new Promise((resolve, reject) => {
      setTimeout(() => resolve(value), 10);
    });
  }

  asyncFunc(__taint_src).then((value) => __taint_sink(value));
}

function __taint_sink(o) {}
