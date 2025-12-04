// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->子类对象
// scene introduction = 子类方法调用
// level = 2
// date = 2025-11-18 14:15:00
// bind_url = completeness/single_app_tracing/interface_class/subclass/method_call_001_T/method_call_001_T
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

type Base struct {
	id int
}

func (b Base) GetID() int {
	return b.id
}

type SubClass struct {
	name string
	Base
}

func (s SubClass) GetName() string {
	return s.name
}

func subclass_method_call_007_T(__taint_src string) {
	// 场景特点：调用子类的实例方法获取字段值
	s := SubClass{
		Base: Base{id: 1},
		name: __taint_src,
	}
	taint_sink(s.GetName())
}

func taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	subclass_method_call_007_T(__taint_src)
}
