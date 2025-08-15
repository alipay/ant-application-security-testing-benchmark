
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// scene introduction = 
// level = 2
// bind_url = completeness/single_app_tracing/datatype/array/array_001_T/array_001_T
// evaluation information end

package main
import "os/exec"

func array_001_T(__taint_src string) {
	var str = [3]string{__taint_src, "b", "c"}
	__taint_sink(str)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    array_001_T(__taint_src)
}