// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->简单对象
// scene introduction = 创建对象->new分配
// level = 2
// date = 2025-11-14 18:40:00
// bind_url = completeness/single_app_tracing/interface_class/simple_object/create_object_003_T/create_object_003_T
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

type Person struct {
	name string
	age  int
}

func create_object_003_T(__taint_src string) {
	// 场景特点：使用new关键字创建结构体实例
	p := new(Person)
	p.name = __taint_src
	p.age = 25
	taint_sink(p.name)
}

func taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	create_object_003_T(__taint_src)
}
