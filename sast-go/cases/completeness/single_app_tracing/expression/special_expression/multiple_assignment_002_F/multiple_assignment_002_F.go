
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = 表达式并行赋值
// level = 2
// bind_url = completeness/single_app_tracing/expression/special_expression/multiple_assignment_002_F/multiple_assignment_002_F
// evaluation information end

package main
import "os/exec"

func multiple_assignment_002_F(__taint_src string) {
	_, result, _ := "_", "_", __taint_src
	__taint_sink(result)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    multiple_assignment_002_F(__taint_src)
}