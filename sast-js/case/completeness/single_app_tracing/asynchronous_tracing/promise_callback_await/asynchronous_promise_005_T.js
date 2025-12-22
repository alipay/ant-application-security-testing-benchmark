// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
// scene introduction = Promise
// level = 2
// bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/asynchronous_promise_005_T
// evaluation information end
const { execSync } = require('child_process');


function asynchronous_promise_005_T(__taint_src) {
  let promise = new Promise((resolve, reject) => {
    resolve(__taint_src);
  });

  promise
    .then((result) => {
      __taint_sink(result);
    })
    .catch((error) => {
      console.error(error);
    });
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

asynchronous_promise_005_T(taint_src);
