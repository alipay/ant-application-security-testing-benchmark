package conditional_if_009_T


// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->能够对上下文条件进行求解，以区分不同执行路径的状态
// scene introduction = if->区分if else准入条件（需求解）
// level = 4
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/solver/conditional_if_009_T/conditional_if_009_T
// evaluation information end

func conditional_if_009_T(__taint_src string) {
	res := ""
	if 1+1 == 2 {
		res = __taint_src
	} else {
		res = "_"
	}
	__taint_sink(res)
}

func __taint_sink(o interface{}) {}
