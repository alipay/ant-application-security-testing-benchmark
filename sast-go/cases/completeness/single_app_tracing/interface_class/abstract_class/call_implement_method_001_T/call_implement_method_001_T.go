// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->抽象类的实现类
// scene introduction = 实现类方法调用
// level = 2
// date = 2025-11-19 15:52:00
// bind_url = completeness/single_app_tracing/interface_class/abstract_class/call_implement_method_001_T/call_implement_method_001_T
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

// 定义抽象接口
type AbstractShape interface {
	ProcessData(data string) string
}

// 实现类
type Circle struct {
	name string
}

func (c *Circle) ProcessData(data string) string {
	// 场景特点：实现类方法处理输入数据并返回
	c.name = data
	return c.name
}

func call_implement_method_001_T(__taint_src string) {
	shape := &Circle{}

	// 调用实现类实现的抽象方法
	result := shape.ProcessData(__taint_src)
	taint_sink(result)
}

func taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	call_implement_method_001_T(__taint_src)
}
