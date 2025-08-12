// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = delete运算符->对象field
// level = 2
// bind_url = completeness/single_app_tracing/expression/special_expression/delete_expression_001_T
// evaluation information end
const { execSync } = require('child_process');

function delete_expression_001_T(__taint_src) {
  const Employee = {
    firstname: "Bob",
    lastname: __taint_src,
  };

  delete Employee.firstname;
  __taint_sink(Employee.lastname);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

delete_expression_001_T(taint_src);
