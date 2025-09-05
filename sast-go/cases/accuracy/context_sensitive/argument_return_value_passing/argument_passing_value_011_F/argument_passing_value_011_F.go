// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/argument_passing_value_011_F/argument_passing_value_011_F
// evaluation information end

package main
import "os/exec"

func argument_passing_value_011_F(__taint_src interface{}) {
	process(__taint_src)
}

func process(arg interface{}) {
	arg = "safe"
	__taint_sink(arg)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    argument_passing_value_011_F(__taint_src)
}