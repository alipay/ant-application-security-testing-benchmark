package fallthrough_004_F


// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感分析->跳转语句
// scene introduction = fallthrough
// level = 4+
// bind_url = accuracy/path_sensitive/explicit_jump_control/fallthrough_004_F/fallthrough_004_F
// evaluation information end

func fallthrough_004_F(__taint_src string) {
	res := ""
	switch 2 {
	case 1:
		fallthrough
	case 2:
		res = __taint_src
		fallthrough
	case 3:
		res = "_"
	default:
	}
	__taint_sink(res)
}

func __taint_sink(o interface{}) {}
