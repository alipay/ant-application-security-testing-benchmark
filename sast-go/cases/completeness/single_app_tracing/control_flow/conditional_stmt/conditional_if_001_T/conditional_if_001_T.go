package conditional_if_001_T


// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->条件语句
// scene introduction = if
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/conditional_if_001_T/conditional_if_001_T
// evaluation information end

func conditional_if_001_T(__taint_src string) {
	if true {
		__taint_sink(__taint_src)
	}
}

func __taint_sink(o interface{}) {}
