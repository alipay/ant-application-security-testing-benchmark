package continue_004_F


// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感分析->跳转语句
// scene introduction = continue
// level = 4+
// bind_url = accuracy/path_sensitive/explicit_jump_control/continue_004_F/continue_004_F
// evaluation information end

func continue_004_F(__taint_src string) {
	res := ""
	for i := 0; i < 10; i++ {
		if i == 3 {
			res = __taint_src
			continue
			__taint_sink(res)
		}
	}
}

func __taint_sink(o interface{}) {}
