// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = 扩展运算符
// level = 2
// bind_url = completeness/single_app_tracing/expression/special_expression/spread_operator_003_T
// evaluation information end
const { execSync } = require('child_process');


function spread_operator_003_T(__taint_src) {
  const params = {
    name: "Tony",
    age: "12",
    id: __taint_src,
  };
  const newParams = {
    score: 100,
    ...params,
  };
  __taint_sink(newParams);
}

function __taint_sink(o) {
  execSync(JSON.stringify(o));
}

const taint_src = "taint_src_value";

spread_operator_003_T(taint_src);
