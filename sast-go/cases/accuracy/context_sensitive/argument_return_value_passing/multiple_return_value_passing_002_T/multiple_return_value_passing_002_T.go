
// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 返回值传递->多返回值传递
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/multiple_return_value_passing_002_T/multiple_return_value_passing_002_T
// evaluation information end

package main
import "os/exec"

func multiple_return_value_passing_002_T(__taint_src interface{}) {
	a := "_"

	ret1, ret2 := processData(__taint_src, a)
	_ = ret2
	__taint_sink(ret1)
}

func processData(s interface{}, i interface{}) (interface{}, interface{}) {
	return s, i
}

func __taint_sink(o interface{}) {  
	_ = exec.Command("sh", "-c", o.(string)).Run()
}

func main() {
    __taint_src := "taint_src_value"
    multiple_return_value_passing_002_T(__taint_src)
}