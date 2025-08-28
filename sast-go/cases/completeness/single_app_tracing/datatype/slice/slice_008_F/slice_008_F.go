
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->切片
// scene introduction = copy操作
// level = 2
// bind_url = completeness/single_app_tracing/datatype/slice/slice_008_F/slice_008_F
// evaluation information end

package main
import (
	"os/exec"
	"fmt"
)

func slice_008_F(__taint_src string) {
	var s []string = []string{__taint_src, "b", "c"}
	var ss []string = []string{"a", "b", "c"}
	_ = s
	s1 := make([]string, 3)
	copy(s1, ss)
	__taint_sink(s1)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    slice_008_F(__taint_src)
}