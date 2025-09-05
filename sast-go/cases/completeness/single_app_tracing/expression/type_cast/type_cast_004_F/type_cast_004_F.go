
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->类型转换
// scene introduction = 类型断言
// level = 2
// bind_url = completeness/single_app_tracing/expression/type_cast/type_cast_004_F/type_cast_004_F
// evaluation information end

package type_cast_004_F

func type_cast_004_F(__taint_src interface{}) {
	result, _ := __taint_src.(string)
	result = "abc"
	__taint_sink(result)
}

func __taint_sink(o interface{}) {
}
