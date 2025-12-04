// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字典
// scene introduction = Map删除操作
// level = 2
// date = 2025-11-28 16:52:19
// bind_url = completeness/single_app_tracing/datatype/map/map_delete_001_T/map_delete_001_T
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

func map_delete_001_T(__taint_src string) {
	// 场景特点：向map添加元素后不删除，保持污染数据
	set := make(map[string]bool)
	set[__taint_src] = true
	// 删除污染元素
	delete(set, __taint_src)
	__taint_sink(set)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	map_delete_001_T(__taint_src)
}
