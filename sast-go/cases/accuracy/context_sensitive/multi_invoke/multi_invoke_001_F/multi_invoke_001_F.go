
// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感分析->多次调用
// scene introduction = 
// level = 2
// bind_url = accuracy/context_sensitive/multi_invoke/multi_invoke_001_F/multi_invoke_001_F
// evaluation information end

package main
import "os/exec"

func multi_invoke_001_F(__taint_src string) {
	a := process(__taint_src)
	b := process("_")
	_ = a
	__taint_sink(b)
}

func process(arg string) string {
	return arg
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    multi_invoke_001_F(__taint_src)
}