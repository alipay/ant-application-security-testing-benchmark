
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->结构体
// scene introduction = 结构体
// level = 2
// bind_url = completeness/single_app_tracing/datatype/struct/struct_004_F/struct_004_F
// evaluation information end

package main
import (
	"os/exec"
	"fmt"
)

type A struct {
	data string
}

func struct_004_F(__taint_src string) {
	p := new(A)
	p.data = __taint_src
	p.data = "_"
	__taint_sink(p)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    struct_004_F(__taint_src)
}