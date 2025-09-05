package main
import "os/exec"


// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
// scene introduction = for init
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/for_init_002_F/for_init_002_F
// evaluation information end

func for_init_002_F(__taint_src int) {
	res := 0
	ini := 0
	j := 0
	for ini = res; j < 10; j++ {
		res = res + ini
	}
	__taint_sink(res)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    for_init_002_F(__taint_src)
}