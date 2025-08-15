
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
// scene introduction = 作用域
// level = 2
// bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/closure_function_009_F/closure_function_009_F
// evaluation information end

package main
import "os/exec"

func closure_function_009_F(__taint_src interface{}) {
	outer := func() func() func() {
		outerVar := __taint_src
		outerVar = "safe"
		middle := func() func() {
			inner := func() {
				__taint_sink(outerVar)
			}

			return inner
		}

		return middle
	}

	closure_function_009_FunctionM := outer()
	closure_function_009_Function := closure_function_009_FunctionM()
	closure_function_009_Function()
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    closure_function_009_F(__taint_src)
}