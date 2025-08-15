package main
import "os/exec"


// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
// scene introduction = for语句update完整度
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/for_update_002_F/for_update_002_F
// evaluation information end

func for_update_002_F(__taint_src int) {
	res := 0
	ini := 0
	j := 0
	for ; ini < 2; j = res {
		res = j
		ini++
	}
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    for_update_002_F(__taint_src)
}