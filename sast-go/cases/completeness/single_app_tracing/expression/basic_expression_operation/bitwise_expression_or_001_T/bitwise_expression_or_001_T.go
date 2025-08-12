
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 位操作->或
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/bitwise_expression_or_001_T/bitwise_expression_or_001_T
// evaluation information end

package main
import "os/exec"

func bitwise_expression_or_001_T(__taint_src int) {
	result := __taint_src | 1
	__taint_sink(result)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    bitwise_expression_or_001_T(__taint_src)
}