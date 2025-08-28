
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 位操作->按位取反
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/bitwise_expression_not_002_F/bitwise_expression_not_002_F
// evaluation information end

package main
import (
	"os/exec"
	"fmt"
)

func bitwise_expression_not_002_F(__taint_src int) {
	result := ^__taint_src
	_ = result
	__taint_sink("aa")
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
	}

func main() {
    __taint_src := 123
    bitwise_expression_not_002_F(__taint_src)
}