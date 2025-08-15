package main
import "os/exec"


// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
// scene introduction = for body
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/for_body_001_T/for_body_001_T
// evaluation information end

func for_body_001_T(__taint_src string) {
	res := "_"

	for i := 0; i < 3; i++ {
		res = __taint_src
	}
	__taint_sink(res)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    for_body_001_T(__taint_src)
}