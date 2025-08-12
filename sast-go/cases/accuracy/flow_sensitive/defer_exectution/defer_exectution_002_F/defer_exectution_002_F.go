
// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感分析->延迟执行
// scene introduction = defer
// level = 3
// bind_url = accuracy/flow_sensitive/defer_exectution/defer_exectution_002_F/defer_exectution_002_F
// evaluation information end

package main
import "os/exec"

func defer_exectution_002_F(__taint_src string) {
	result := "aa"
	defer func() {
		result = __taint_src
	}()
	___taint_sink(result)
}

func ___taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
}

func main() {
    __taint_src := "taint_src_value"
    defer_exectution_002_F(__taint_src)
}