// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->结构体
// scene introduction = 结构体指针字段访问
// level = 2
// bind_url = completeness/single_app_tracing/datatype/struct/struct_pointer_002_F/struct_pointer_002_F
// date = 2025-12-01 14:35:05
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

type A struct {
	data string
}

func struct_pointer_002_F(__taint_src string) {
	p := &A{
		data: "_",
	}
	// 场景特点：通过指针访问结构体字段，但污点数据未传播到该字段
	__taint_sink(p.data)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	struct_pointer_002_F(__taint_src)
}
