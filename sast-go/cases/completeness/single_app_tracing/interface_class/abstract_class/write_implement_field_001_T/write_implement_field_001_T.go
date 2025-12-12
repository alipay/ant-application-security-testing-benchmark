// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->抽象类的实现类
// scene introduction = 实现类字段写入
// level = 2
// date = 2025-11-19 15:52:00
// bind_url = completeness/single_app_tracing/interface_class/abstract_class/write_implement_field_001_T/write_implement_field_001_T
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

// 定义抽象接口
type AbstractShape interface {
	GetName() string
	SetName(name string)
}

// 实现类
type Circle struct {
	Name string // 导出字段
}

func (c *Circle) GetName() string {
	return c.Name
}

func (c *Circle) SetName(name string) {
	c.Name = name
}

func write_implement_field_001_T(__taint_src string) {
	// 场景特点：向实现类对象的导出字段赋值
	shape := &Circle{}

	// 通过类型断言获取具体类型并写入字段
	shape.Name = __taint_src

	taint_sink(shape.GetName())
}

func taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	write_implement_field_001_T(__taint_src)
}
