package main
import "os/exec"


// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->条件语句
// scene introduction = else if
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/conditional_if_004_F/conditional_if_004_F
// evaluation information end

func conditional_if_004_F(__taint_src string) {
	res := ""
	if false {

	} else if true {
		__taint_sink(res)
	} else {

	}
	res = __taint_src
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    conditional_if_004_F(__taint_src)
}