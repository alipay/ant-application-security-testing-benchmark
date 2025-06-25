// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
// scene introduction = promisify异步分析
// level = 2
// bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/promisify_002_F
// evaluation information end
const { util } = import("util");

function customReadFile(filePath, callback) {
  setTimeout(() => {
    if (filePath) {
      callback(null, `Data from ${filePath}`);
    } else {
      callback(new Error("Invalid file path"));
    }
  }, 100);
}

async function promisify_002_F(__taint_src) {
  const readFileAsync = util.promisify(customReadFile);
  try {
    let data = await readFileAsync("aa");
    __taint_sink(data);
  } catch (error) {
    console.error(error);
  }
}

function __taint_sink(o) { }
