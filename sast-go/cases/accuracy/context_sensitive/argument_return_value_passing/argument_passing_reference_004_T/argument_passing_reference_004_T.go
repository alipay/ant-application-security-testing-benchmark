
// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 参数值传递->引用传递->数组
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/argument_passing_reference_004_T/argument_passing_reference_004_T
// evaluation information end

package main
import(
	"fmt"
	"os/exec"
)

func argument_passing_reference_004_T(__taint_src interface{}) {
	arr := []interface{}{"_"}
	process(arr, __taint_src)
	__taint_sink(arr)
}

func process(inputArr []interface{}, src interface{}) {
	inputArr[0] = src
}

func __taint_sink(o []interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
    __taint_src := "taint_src_value"
    argument_passing_reference_004_T(__taint_src)
}