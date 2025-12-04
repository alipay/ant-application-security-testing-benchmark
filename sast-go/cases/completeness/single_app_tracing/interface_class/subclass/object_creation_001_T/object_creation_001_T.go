// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->子类对象
// scene introduction = 子类对象创建
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/subclass/object_creation_001_T/object_creation_001_T
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

func object_creation_001_T(__taint_src string) {
	// 场景特点：使用字面值初始化子类结构体
	s := SubClass{
		Base: Base{id: 1},
		name: __taint_src,
	}
	taint_sink(s.name)
}

func taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	object_creation_001_T(__taint_src)
}
