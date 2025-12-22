// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->this表达式
// scene introduction = this表达式
// level = 2
// bind_url = completeness/single_app_tracing/expression/this_expression/this_expression_001_T
// evaluation information end
const { execSync } = require('child_process');


function this_expression_001_T(__taint_src) {
  let obj = {
    value: __taint_src,
    getValue: function () {
      return this.value;
    },
  };

  let result = obj.getValue();
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

this_expression_001_T(taint_src);
