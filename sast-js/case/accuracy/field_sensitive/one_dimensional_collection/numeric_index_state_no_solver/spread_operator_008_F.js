// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
// scene introduction = 数组索引->扩展运算符
// level = 3
// bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_no_solver/spread_operator_007_T
// evaluation information end
const { execSync } = require('child_process');


function spread_operator_008_F(__taint_src) {
  const foo = __taint_src;
  const bar = "hello world";
  const [r1, r2, ...rest] = [123, 456, foo, bar];
  __taint_sink(rest[1]);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

spread_operator_008_F(taint_src);
