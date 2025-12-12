// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->接口与类->子类对象
// scene introduction = 子类字段写入
// level = 2
// date = 2025-11-18 14:15:00
// bind_url = completeness/single_app_tracing/interface_class/subclass/field_write_002_F/field_write_002_F
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

type Base struct {
	id int
}

type SubClass struct {
	name string
	Base
}

func subclass_field_write_006_F(__taint_src string) {
	// 场景特点：给子类结构体字段直接赋值但使用安全值
	var s SubClass
	s.id = 1
	s.name = "safe_value"
	taint_sink(s.name)
}

func taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	subclass_field_write_006_F(__taint_src)
}
