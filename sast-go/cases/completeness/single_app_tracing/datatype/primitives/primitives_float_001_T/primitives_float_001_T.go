
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
// scene introduction = 浮点型
// level = 2
// bind_url = completeness/single_app_tracing/datatype/primitives/primitives_float_001_T/primitives_float_001_T
// evaluation information end

package main
import (
	"os/exec"
	"fmt"
)

func primitives_float_001_T(__taint_src float64) {
	__taint_sink(__taint_src)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
	}

func main() {
    __taint_src := 3
		primitives_float_001_T(float64(__taint_src))
}