package conditional_switch_stmt_008_F


// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->能够对上下文条件进行求解，以区分不同执行路径的状态
// scene introduction = switch->区分switch具体路径（求解）
// level = 4
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/solver/conditional_switch_stmt_008_F/conditional_switch_stmt_008_F
// evaluation information end

func conditional_switch_stmt_008_F(__taint_src string) {
	res := ""
	switch 1 + 1 {
	case 2:
		res = __taint_src
	case 3:
		res = "_"
	}
	__taint_sink(res)
}

func __taint_sink(o interface{}) {}
