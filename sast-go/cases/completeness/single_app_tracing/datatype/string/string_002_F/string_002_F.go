
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字符串
// scene introduction = 字符串类型
// level = 2
// bind_url = completeness/single_app_tracing/datatype/string/string_002_F/string_002_F
// evaluation information end

package main
import "os/exec"

func string_002_F(__taint_src string) {
	var sani string = __taint_src
	sani = "_"
	__taint_sink(sani)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    string_002_F(__taint_src)
}