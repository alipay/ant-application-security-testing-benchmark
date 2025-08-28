// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
// scene introduction = 数组索引
// level = 3
// bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_no_solver/array_no_solver_004_F
// evaluation information end
const { execSync } = require('child_process');


function array_no_solver_004_F(__taint_src) {
  let s = [__taint_src, "b", "c"];
  s[0] = "_";
  __taint_sink(s);
}

function __taint_sink(o) {
  execSync(JSON.stringify(o));
}

const taint_src = "taint_src_value";

array_no_solver_004_F(taint_src);
