
// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 参数值传递->引用传递->数组
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/argument_passing_reference_003_F/argument_passing_reference_003_F
// evaluation information end

package main
import "os/exec"

func argument_passing_reference_003_F(__taint_src interface{}) {
	arr := []interface{}{__taint_src}
	process(arr)
	__taint_sink(arr)
}

func process(inputArr []interface{}) {
	inputArr[0] = "changed"
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    argument_passing_reference_003_F(__taint_src)
}