
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// scene introduction = 
// level = 2
// bind_url = completeness/single_app_tracing/datatype/array/array_006_F/array_006_F
// evaluation information end

package main
import "os/exec"

func array_006_F(__taint_src [3]string) {
	sani := __taint_src
	sani = [3]string{"a", "b", "c"}
	__taint_sink(sani)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    array_006_F(__taint_src)
}