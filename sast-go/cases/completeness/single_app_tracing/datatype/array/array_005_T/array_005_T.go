
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// scene introduction = 
// level = 2
// bind_url = completeness/single_app_tracing/datatype/array/array_005_T/array_005_T
// evaluation information end

package main
import (
	"os/exec"
	"fmt"
)

func array_005_T(__taint_src [3]string) {
	__taint_sink(__taint_src)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
	fmt.Printf("%v", o)
	}

func main() {
    __taint_src := "taint_src_value"
    array_005_T([3]string{__taint_src, "", ""})
}