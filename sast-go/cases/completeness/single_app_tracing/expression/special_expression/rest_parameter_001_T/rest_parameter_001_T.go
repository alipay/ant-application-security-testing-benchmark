
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = 扩展运算符
// level = 2
// bind_url = completeness/single_app_tracing/expression/special_expression/rest_parameter_001_T/rest_parameter_001_T
// evaluation information end

package main
import (
	"os/exec"
	"fmt"
)

func rest_parameter_001_T(__taint_src string) {
	collectArgs("prefix", __taint_src, "suffix")
}

func collectArgs(args ...interface{}) {
	__taint_sink(args)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
	fmt.Println(o)
	}

func main() {
    __taint_src := "taint_src_value"
    rest_parameter_001_T(__taint_src)
}