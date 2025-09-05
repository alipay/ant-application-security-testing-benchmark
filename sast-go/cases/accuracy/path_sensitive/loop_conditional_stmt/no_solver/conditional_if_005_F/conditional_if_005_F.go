package conditional_if_005_F


// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态
// scene introduction = if->区分if else扁平化与分支（不求解）
// level = 3
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/no_solver/conditional_if_005_F/conditional_if_005_F
// evaluation information end

func conditional_if_005_F(__taint_src string) {
	var res string
	if true {
		res = __taint_src
	} else {
		__taint_sink(res)
	}
}

func __taint_sink(o interface{}) {}
