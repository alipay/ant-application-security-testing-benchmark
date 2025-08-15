
// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感分析->多次调用
// scene introduction = 
// level = 2
// bind_url = accuracy/context_sensitive/multi_invoke/multi_invoke_002_T/multi_invoke_002_T
// evaluation information end

package main
import "os/exec"

func multi_invoke_002_T(__taint_src string) {
	a := process(__taint_src)
	b := process("_")
	_ = b
	__taint_sink(a)
}

func process(arg string) string {
	return arg
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    multi_invoke_002_T(__taint_src)
}