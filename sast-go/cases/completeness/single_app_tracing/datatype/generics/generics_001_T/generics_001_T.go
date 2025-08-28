
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->泛型
// scene introduction = 
// level = 2
// bind_url = completeness/single_app_tracing/datatype/generics/generics_001_T/generics_001_T
// evaluation information end

package main
import (
	"os/exec"
	"fmt"
)

//泛型要在go版本为1.18及以上才可以使用
type Slice[T int | string | float64] []T

func generics_001_T(__taint_src string) {
	var s Slice[string] = []string{"a", "b", __taint_src}
	__taint_sink(s)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    generics_001_T(__taint_src)
}