
// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
// scene introduction = map->对象敏感
// level = 2
// bind_url = accuracy/object_sensitive/collection/map_obj_sensitive_001_T/map_obj_sensitive_001_T
// evaluation information end

package main
import (
	"os/exec"
	"fmt"
)

func map_obj_sensitive_001_T(__taint_src string) {
	m := make(map[string]string, 1)
	m2 := make(map[string]string, 1)
	m["key1"] = __taint_src
	m2["key1"] = "value"
	__taint_sink(m)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    map_obj_sensitive_001_T(__taint_src)
}