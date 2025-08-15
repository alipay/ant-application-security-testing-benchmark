
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = 多函数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_value_003_F/argument_passing_value_003_F
// evaluation information end

package main
import "os/exec"

func argument_passing_value_003_F(__taint_src interface{}) {
	var clean string = "_"
	process1(clean)
	process2(__taint_src)
}

func process1(arg interface{}) {
	__taint_sink(arg)
}

func process2(arg interface{}) {
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    argument_passing_value_003_F(__taint_src)
}