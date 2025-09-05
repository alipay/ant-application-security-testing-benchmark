package break_label_004_F


// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感分析->跳转语句
// scene introduction = break_label
// level = 4+
// bind_url = accuracy/path_sensitive/explicit_jump_control/break_label_004_F/break_label_004_F
// evaluation information end

func break_label_004_F(__taint_src string) {
	res := ""

myLabel:
	for i := 0; i < 2; i++ {
		break myLabel
		res = __taint_src
	}

	__taint_sink(res)
}

func __taint_sink(o interface{}) {}
