package main
import "os/exec"


// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态
// scene introduction = if->区分if else具体路径（不求解）
// level = 3
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/no_solver/conditional_if_no_solver_003_T/conditional_if_no_solver_003_T
// evaluation information end

func conditional_if_no_solver_003_T(__taint_src string) {
	res := ""
	if true {
		res = __taint_src
	} else {
		res = "_"
	}
	__taint_sink(res)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    conditional_if_no_solver_003_T(__taint_src)
}