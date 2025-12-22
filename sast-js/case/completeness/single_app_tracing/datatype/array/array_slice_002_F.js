// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// scene introduction = 数组切片
// level = 2
// bind_url = completeness/single_app_tracing/datatype/array/array_slice_002_F
// date = 2025-12-16 09:24:29
// evaluation information end
const { execSync } = require("child_process");

function array_slice_002_F(__taint_src) {
  // 场景特点：使用slice方法截取不包含污点的数组片段
  let arr = ["safe_value", __taint_src];
  let sliced = arr.slice(0, 1);
  __taint_sink(sliced);
}

function __taint_sink(o) {
  execSync(JSON.stringify(o));
}

const taint_src = "taint_src_value";
array_slice_002_F(taint_src);
