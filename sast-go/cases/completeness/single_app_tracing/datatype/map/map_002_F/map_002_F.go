
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字典
// scene introduction = 字典/映射（Map）对象
// level = 2
// bind_url = completeness/single_app_tracing/datatype/map/map_002_F/map_002_F
// evaluation information end

package main
import (
	"os/exec"
	"fmt"
)

func map_002_F(__taint_src string) {
	m := make(map[string]string, 1)
	m["key1"] = "_"
	__taint_sink(m)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    map_002_F(__taint_src)
}