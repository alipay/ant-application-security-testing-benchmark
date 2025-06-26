// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->this表达式
// scene introduction = this表达式
// level =  2
// bind_url = completeness/single_app_tracing/expression/this_expression/this_expression_002_F
// evaluation information end

function this_expression_002_F(__taint_src) {
  let obj = {
    value: "aa",
    getValue: function () {
      return this.value;
    },
  };

  let result = obj.getValue();
  __taint_sink(result);
}

function __taint_sink(o) { }
