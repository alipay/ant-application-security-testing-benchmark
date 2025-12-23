// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->多线程
// scene introduction = 多线程
// level = 2
// bind_url = completeness/single_app_tracing/asynchronous_tracing/multi_thread/multi_thread_001_T
// date = 2025-12-18 02:39:00
// evaluation information end
const { execSync } = require("child_process");
const { Worker } = require("worker_threads");
const path = require("path");

function multi_thread_001_T(__taint_src) {
  const worker = new Worker(path.join(__dirname, "worker.js"));

  worker.on("message", (data) => {
    __taint_sink(data);
  });

  worker.on("error", (e) => {
    console.log(e);
  });

  worker.postMessage(__taint_src);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";
multi_thread_001_T(taint_src);
