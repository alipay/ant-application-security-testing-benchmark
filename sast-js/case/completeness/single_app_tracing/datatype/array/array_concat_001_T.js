// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// scene introduction = 数组连接
// level = 2
// bind_url = completeness/single_app_tracing/datatype/array/array_concat_001_T
// date = 2025-12-16 09:24:29
// evaluation information end
const { execSync } = require('child_process');

function array_concat_001_T(__taint_src) {
  // 场景特点：使用concat方法连接包含污点的数组
  let arr1 = [__taint_src];
  let arr2 = ["b", "c"];
  let result = arr1.concat(arr2);
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(JSON.stringify(o));
}

const taint_src = "taint_src_value";
array_concat_001_T(taint_src);