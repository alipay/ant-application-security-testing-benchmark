// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// scene introduction = 数组过滤
// level = 2
// bind_url = completeness/single_app_tracing/datatype/array/array_filter_002_F
// date = 2025-12-16 09:24:29
// evaluation information end
const { execSync } = require("child_process");

function array_filter_002_F(__taint_src) {
  // 场景特点：使用filter方法过滤后排除污点元素
  let arr = [__taint_src, "safe_value"];
  let filtered = arr.filter((item) => item !== __taint_src);
  __taint_sink(filtered);
}

function __taint_sink(o) {
  execSync(JSON.stringify(o));
}

const taint_src = "taint_src_value";
array_filter_002_F(taint_src);
