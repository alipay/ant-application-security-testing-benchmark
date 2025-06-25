package for_body_003_T


// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->能够对上下文条件进行求解，以区分不同执行路径的状态
// scene introduction = for_body
// level = 4
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/solver/for_body_003_T/for_body_003_T
// evaluation information end

func for_body_003_T(__taint_src string) {
	var res string
	for i := 0; i < 2; i++ {
		res = __taint_src
	}
	__taint_sink(res)
}

func __taint_sink(o interface{}) {}
