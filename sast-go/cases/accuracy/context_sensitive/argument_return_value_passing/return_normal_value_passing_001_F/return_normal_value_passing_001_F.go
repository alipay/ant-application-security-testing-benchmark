// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/return_normal_value_passing_001_F/return_normal_value_passing_001_F
// evaluation information end

package main
import "os/exec"

func return_normal_value_passing_001_F(__taint_src string) {
	data := process(__taint_src)
	__taint_sink(data)
}

func process(__taint_src string) string {
	tmp := __taint_src
	tmp = "_"
	return tmp
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    return_normal_value_passing_001_F(__taint_src)
}