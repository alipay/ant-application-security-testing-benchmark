
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
// scene introduction = 整型
// level = 2
// bind_url = completeness/single_app_tracing/datatype/primitives/primitives_int_001_T/primitives_int_001_T
// evaluation information end

package main
import (
	"os/exec"
	"fmt"
)

func primitives_int_001_T(__taint_src int) {
	__taint_sink(__taint_src)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
	fmt.Printf("%v", o)
	}

func main() {
    __taint_src := 123
    primitives_int_001_T(__taint_src)
}