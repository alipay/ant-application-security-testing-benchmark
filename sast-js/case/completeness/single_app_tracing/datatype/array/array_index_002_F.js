// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// scene introduction = 数组索引读取
// level = 2
// bind_url = completeness/single_app_tracing/datatype/array/array_index_002_F
// date = 2025-12-16 09:24:29
// evaluation information end
const { execSync } = require("child_process");

function array_index_002_F(__taint_src) {
  // 场景特点：通过索引访问数组中的安全元素
  let arr = ["safe_value", __taint_src];
  __taint_sink(arr[0]);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";
array_index_002_F(taint_src);
