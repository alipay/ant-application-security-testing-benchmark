package main
import "os/exec"


// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
// scene introduction = for range
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/for_range_002_F/for_range_002_F
// evaluation information end

func for_range_002_F(__taint_src string) {
	var m = map[string]string{"a": "_"}
	for _, res := range m {
		__taint_sink(res)
	}
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    for_range_002_F(__taint_src)
}