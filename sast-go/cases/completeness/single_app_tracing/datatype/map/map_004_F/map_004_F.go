
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字典
// scene introduction = 字典/映射（Map）对象
// level = 2
// bind_url = completeness/single_app_tracing/datatype/map/map_004_F/map_004_F
// evaluation information end

package main
import (
	"os/exec"
	"fmt"
)

func map_004_F(__taint_src string) {
	m := map[string]string{
		"key1": "value1",
	}
	__taint_sink(m)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    map_004_F(__taint_src)
}