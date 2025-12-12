// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->接口与类->子类对象
// scene introduction = 子类对象创建
// level = 2
// date = 2025-11-18 14:15:00
// bind_url = completeness/single_app_tracing/interface_class/subclass/object_creation_002_F/object_creation_002_F
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

func subclass_object_creation_002_F(__taint_src string) {
	// 场景特点：使用字面值初始化子类结构体但使用安全值
	s := SubClass{
		Base: Base{id: 1},
		name: "safe_value",
	}
	taint_sink(s.name)
}

func taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	subclass_object_creation_002_F(__taint_src)
}
