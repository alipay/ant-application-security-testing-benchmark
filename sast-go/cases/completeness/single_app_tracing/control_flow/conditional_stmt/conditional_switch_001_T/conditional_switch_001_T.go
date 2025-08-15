package main
import "os/exec"


// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->条件语句
// scene introduction = switch
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/conditional_switch_001_T/conditional_switch_001_T
// evaluation information end

func conditional_switch_001_T(__taint_src string) {
	switch 2 {
	case 2:
		__taint_sink(__taint_src)
	}
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    conditional_switch_001_T(__taint_src)
}