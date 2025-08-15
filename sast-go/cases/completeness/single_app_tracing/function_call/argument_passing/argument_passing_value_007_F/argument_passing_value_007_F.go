
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = 嵌套函数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_value_007_F/argument_passing_value_007_F
// evaluation information end

package main
import "os/exec"

func argument_passing_value_007_F(__taint_src string) {
	outer := func(input string) {
		inner := func(innerInput interface{}) {
			__taint_sink(innerInput)
		}
		input = "safe"
		inner(input)
	}

	outer(__taint_src)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    argument_passing_value_007_F(__taint_src)
}