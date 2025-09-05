package main
import "os/exec"


// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->条件语句
// scene introduction = switch嵌套
// level = 2+
// bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/conditional_switch_006_F/conditional_switch_006_F
// evaluation information end

func conditional_switch_006_F(__taint_src string) {
	x := 2
	y := 3

	switch x {
	case 2:
		switch y {
		case 2:
			__taint_sink(__taint_src)
		case 3:
			__taint_sink(y)
		}
	}
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    conditional_switch_006_F(__taint_src)
}