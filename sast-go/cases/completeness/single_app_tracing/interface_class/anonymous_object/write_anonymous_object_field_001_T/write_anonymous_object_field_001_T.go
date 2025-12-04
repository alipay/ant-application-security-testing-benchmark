// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->匿名对象
// scene introduction = 写入匿名对象字段
// level = 2
// date = 2025-11-19 15:44:00
// bind_url = completeness/single_app_tracing/interface_class/anonymous_object/write_anonymous_object_field_001_T/write_anonymous_object_field_001_T
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

func write_anonymous_object_field_003_T(__taint_src string) {
	// 场景特点：向匿名对象的字段写入污染数据
	person := struct {
		name string
	}{
		name: "initial",
	}

	// 场景特点：直接给匿名对象字段赋值
	person.name = __taint_src
	taint_sink(person.name)
}

func taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	write_anonymous_object_field_003_T(__taint_src)
}
