// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
// scene introduction = 结构体标签字段处理
// level = 2
// date = 2025-11-17 14:39:00
// bind_url = completeness/single_app_tracing/interface_class/complex_object/struct_tag_field_001_T/struct_tag_field_001_T
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
	"reflect"
)

type Person struct {
	Name string `json:"name"` // 带标签的字段
	Age  int    `json:"age"`
}

func struct_tag_field_001_T(__taint_src string) {
	// 场景特点：通过反射访问带标签的结构体字段
	person := Person{
		Name: __taint_src,
		Age:  25,
	}

	// 使用反射获取字段值
	v := reflect.ValueOf(person)
	field := v.FieldByName("Name")
	if field.IsValid() {
		taint_sink(field.String())
	}
}

func taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	struct_tag_field_001_T(__taint_src)
}
