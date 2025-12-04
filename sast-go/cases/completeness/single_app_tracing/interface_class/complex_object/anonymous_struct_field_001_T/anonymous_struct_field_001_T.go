// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
// scene introduction = 匿名结构体字段访问
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/complex_object/anonymous_struct_field_001_T/anonymous_struct_field_001_T
// date: 2025-11-17 14:38:00
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

func anonymous_struct_field_001_T(__taint_src string) {
	// 场景特点：访问匿名结构体的字段
	person := struct {
		name string
	}{
		name: __taint_src,
	}
	taint_sink(person.name) // 直接访问匿名结构体的字段
}

func taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	anonymous_struct_field_001_T(__taint_src)
}
