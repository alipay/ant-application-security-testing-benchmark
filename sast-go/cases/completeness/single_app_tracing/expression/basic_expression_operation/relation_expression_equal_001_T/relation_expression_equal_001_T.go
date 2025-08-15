
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 关系操作->等于
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/relation_expression_equal_001_T/relation_expression_equal_001_T
// evaluation information end

package main
import "os/exec"

func relation_expression_equal_001_T(__taint_src string) {
	result := __taint_src == "__taint_src"
	__taint_sink(result)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    relation_expression_equal_001_T(__taint_src)
}