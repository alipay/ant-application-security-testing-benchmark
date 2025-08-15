
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = 空白标识符
// level = 2
// bind_url = completeness/single_app_tracing/expression/special_expression/blank_identifier_001_T/blank_identifier_001_T
// evaluation information end

package main
import "os/exec"

func blank_identifier_001_T(__taint_src string) {
	a, _ := getData(__taint_src)
	__taint_sink(a)
}

func getData(__taint_src string) (string, string) {
	return __taint_src, ""
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    blank_identifier_001_T(__taint_src)
}