// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// scene introduction = 数组索引
// level = 2
// date = 2025-11-28 16:27:50
// bind_url = completeness/single_app_tracing/datatype/array/array_index_002_F/array_index_002_F
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

func array_index_002_F(__taint_src string) {
	var arr = [3]string{__taint_src, "b", "c"}
	arr[0] = "safe_value"
	__taint_sink(arr[0])
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	array_index_002_F(__taint_src)
}
