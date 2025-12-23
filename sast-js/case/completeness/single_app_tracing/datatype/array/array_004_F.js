// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// scene introduction = 二维
// level = 2
// bind_url = completeness/single_app_tracing/datatype/array/array_004_F
// evaluation information end
const { execSync } = require('child_process');


function array_004_F(__taint_src) {
  let s2 = [["a"], ["b"], "c"];
  __taint_sink(s2);
}

function __taint_sink(o) {
  execSync(JSON.stringify(o));
}

const taint_src = "taint_src_value";

array_004_F(taint_src);
