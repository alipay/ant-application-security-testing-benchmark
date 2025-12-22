// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = 可选链操作符
// level = 2
// bind_url = completeness/single_app_tracing/expression/special_expression/optional_chaining_operator_001_T
// evaluation information end
const { execSync } = require('child_process');


function optional_chaining_operator_001_T(__taint_src) {
  let obj = {
    taintKey: __taint_src,
  };

  let result = obj?.taintKey;
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

optional_chaining_operator_001_T(taint_src);
