package conditional_switch_002_F


// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->条件语句
// scene introduction = switch
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/conditional_switch_002_F/conditional_switch_002_F
// evaluation information end

func conditional_switch_002_F(__taint_src string) {
	data := ""
	switch 2 {
	case 2:
		__taint_sink(data)
	}
	data = __taint_src
}

func __taint_sink(o interface{}) {}
