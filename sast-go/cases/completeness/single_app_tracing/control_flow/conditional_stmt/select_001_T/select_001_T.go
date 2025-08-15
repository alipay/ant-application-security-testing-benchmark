package main
import "os/exec"


// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->条件语句
// scene introduction = select
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/select_001_T/select_001_T
// evaluation information end

func select_001_T(__taint_src string) {
	ch1 := make(chan string, 1)
	ch1 <- __taint_src

	select {
	case data := <-ch1:
		__taint_sink(data)
	default:

	}
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    select_001_T(__taint_src)
}