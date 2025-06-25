package conditional_switch_stmt_005_T


// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态
// scene introduction = switch->区分switch具体路径（不求解）
// level = 3
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/no_solver/conditional_switch_stmt_005_T/conditional_switch_stmt_005_T
// evaluation information end

func conditional_switch_stmt_005_T(__taint_src string) {
	res := ""
	switch 2 {
	case 1:
		res = "_"
	case 2:
		res = __taint_src
	}
	__taint_sink(res)
}

func __taint_sink(o interface{}) {}
