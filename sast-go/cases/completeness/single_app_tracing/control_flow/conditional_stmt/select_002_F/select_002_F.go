package select_002_F


// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->条件语句
// scene introduction = select
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/select_002_F/select_002_F
// evaluation information end

func select_002_F(__taint_src string) {
	ch1 := make(chan string, 1)
	ch1 <- "_"

	select {
	case data := <-ch1:
		__taint_sink(data)
	default:

	}
}

func __taint_sink(o interface{}) {}
