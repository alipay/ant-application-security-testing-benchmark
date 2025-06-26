package return_003_T


// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->跳转语句
// scene introduction = return
// level = 4+
// bind_url = accuracy/path_sensitive/explicit_jump_control/return/return_003_T/return_003_T
// evaluation information end

func return_003_T(__taint_src string) string {
	if false {
		return __taint_src
	}
	__taint_sink(__taint_src)
	return ""
}

func __taint_sink(o interface{}) {}
