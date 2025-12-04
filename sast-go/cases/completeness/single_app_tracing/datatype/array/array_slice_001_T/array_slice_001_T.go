// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// scene introduction = 数组切片操作
// level = 2
// date = 2025-11-28 16:27:50
// bind_url = completeness/single_app_tracing/datatype/array/array_slice_001_T/array_slice_001_T
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

func array_slice_001_T(__taint_src string) {
	// 场景特点：数组切片操作传递污染数据
	var arr = [3]string{__taint_src, "b", "c"}
	slice := arr[0:1]
	__taint_sink(slice)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	array_slice_001_T(__taint_src)
}
