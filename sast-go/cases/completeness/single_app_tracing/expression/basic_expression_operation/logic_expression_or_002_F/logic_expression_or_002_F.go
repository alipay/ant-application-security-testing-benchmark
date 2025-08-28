
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 逻辑表达式->或表达式
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/logic_expression_or_002_F/logic_expression_or_002_F
// evaluation information end

package main
import (
	"os/exec"
	"fmt"
)

func logic_expression_or_002_F(__taint_src bool) {
	result := false || __taint_src
	result = false
	__taint_sink(result)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
	}

func main() {
    __taint_src := false
    logic_expression_or_002_F(__taint_src)
}