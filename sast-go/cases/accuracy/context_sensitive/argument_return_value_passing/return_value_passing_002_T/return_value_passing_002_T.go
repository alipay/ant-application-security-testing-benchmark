// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/return_value_passing_002_T/return_value_passing_002_T
// evaluation information end

package main
import "os/exec"

func return_value_passing_002_T(__taint_src interface{}) {
	data := process(__taint_src)
	__taint_sink(data)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func process(__taint_src interface{}) interface{} {
	return __taint_src
}

func main() {
    __taint_src := "taint_src_value"
    return_value_passing_002_T(__taint_src)
}