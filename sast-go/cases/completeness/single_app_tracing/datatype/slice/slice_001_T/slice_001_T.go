
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->切片
// scene introduction = 
// level = 2
// bind_url = completeness/single_app_tracing/datatype/slice/slice_001_T/slice_001_T
// evaluation information end

package main
import (
	"os/exec"
	"fmt"
)

func slice_001_T(__taint_src string) {
	var s []string = []string{__taint_src, "b", "c"}
	__taint_sink(s)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    slice_001_T(__taint_src)
}