// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = delete运算符->对象field
// level = 2
// bind_url = completeness/single_app_tracing/expression/special_expression/delete_expression_002_F
// evaluation information end
function delete_expression_002_F(__taint_src) {
  const Employee = {
    firstname: "Bob",
    lastname: __taint_src,
  };

  delete Employee.lastname;
  __taint_sink(Employee.lastname);
}

function __taint_sink(o) { }
