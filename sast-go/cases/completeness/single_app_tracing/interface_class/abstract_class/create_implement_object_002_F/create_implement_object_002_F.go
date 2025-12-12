// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->接口与类->抽象类的实现类
// scene introduction = 创建实现类对象
// level = 2
// date = 2025-11-19 14:32:00
// bind_url = completeness/single_app_tracing/interface_class/abstract_class/create_implement_object_002_F/create_implement_object_002_F
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
	name string
}

func (c *Circle) GetName() string {
	return c.name
}

func (c *Circle) SetName(name string) {
	c.name = name
}

func create_implement_object_002_F(__taint_src string) {
	// 场景特点：通过抽象类引用创建实现类实例，但数据流中断
	shape := AbstractShape(&Circle{
		name: "safe_value", // 使用安全值而非污点源
	})
	taint_sink(shape.GetName())
}

func taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	create_implement_object_002_F(__taint_src)
}
