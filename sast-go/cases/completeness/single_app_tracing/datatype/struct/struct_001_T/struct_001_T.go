
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->结构体
// scene introduction = 结构体
// level = 2
// bind_url = completeness/single_app_tracing/datatype/struct/struct_001_T/struct_001_T
// evaluation information end

package main
import (
	"os/exec"
	"fmt"
)

type A struct {
	data string
}

func struct_001_T(__taint_src string) {
	p := A{
		data: __taint_src,
	}
	__taint_sink(p)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    struct_001_T(__taint_src)
}