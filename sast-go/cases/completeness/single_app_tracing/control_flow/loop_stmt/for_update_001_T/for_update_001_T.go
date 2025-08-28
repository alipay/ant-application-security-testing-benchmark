package main
import (
	"os/exec"
	"fmt"
)


// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
// scene introduction = for语句update完整度
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/for_update_001_T/for_update_001_T
// evaluation information end

func for_update_001_T(__taint_src int) {
	res := 0
	ini := 0
	j := 0
	for ; ini < 2; j = __taint_src {
		res = j
		ini++
	}
	__taint_sink(res)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
	}

func main() {
    __taint_src := 3
    for_update_001_T(__taint_src)
}