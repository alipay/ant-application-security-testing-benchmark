// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->简单对象
// scene introduction = 创建对象->结构体初始化
// level = 2
// date = 2025-11-14 18:40:00
// bind_url = completeness/single_app_tracing/interface_class/simple_object/create_object_001_T/create_object_001_T
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

func create_object_001_T(__taint_src string) {
	// 场景特点：使用字面值初始化结构体
	p := Person{
		name: __taint_src,
		age:  25,
	}
	taint_sink(p.name)
}

func taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	create_object_001_T(__taint_src)
}
