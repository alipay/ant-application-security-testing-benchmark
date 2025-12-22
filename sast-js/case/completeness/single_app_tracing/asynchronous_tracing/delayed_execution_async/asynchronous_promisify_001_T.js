// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
// scene introduction = promisify异步分析
// level = 2
// bind_url = completeness/single_app_tracing/asynchronous_tracing/delayed_execution_async/asynchronous_promisify_001_T
// evaluation information end
const { execSync } = require('child_process');

const { promisify } = require("util");

function customReadFile(filePath, callback) {
  setTimeout(() => {
    if (filePath) {
      callback(null, `Data from ${filePath}`);
    } else {
      callback(new Error("Invalid file path"));
    }
  }, 100);
}

async function asynchronous_promisify_001_T(__taint_src) {
  const readFileAsync = promisify(customReadFile);
  try {
    let data = await readFileAsync(__taint_src);
    __taint_sink(data);
  } catch (error) {
    console.error(error);
  }
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

asynchronous_promisify_001_T(taint_src);
