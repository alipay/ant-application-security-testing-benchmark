package return_004_F


// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感分析->跳转语句
// scene introduction = return
// level = 4+
// bind_url = accuracy/path_sensitive/explicit_jump_control/return/return_004_F/return_004_F
// evaluation information end

func return_004_F(__taint_src string) string {
	if true {
		return __taint_src
	}
	__taint_sink(__taint_src)
	return ""
}

func __taint_sink(o interface{}) {}
