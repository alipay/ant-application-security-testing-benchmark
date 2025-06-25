// evaluation information start
// real case = true
// evaluation item =完整度->单应用跟踪完整度->数据类型和结构->结构体
// scene introduction = 复杂结构体
// level = 2
// bind_url = completeness/single_app_tracing/datatype/struct/complex_struct_002_F/complex_struct_002_F
// evaluation information end

package main

import "fmt"

func complex_struct_002_F(__taint_src interface{}) {
	var a A
	a.Say(__taint_src)
	a.b.Say("_")
}

type A struct {
	b B
}

func (a A) Say(c interface{}) {
	fmt.Println("Hello from A!")
}

type B struct{}

func (b B) Say(c interface{}) {
	__taint_sink(c)
}

func __taint_sink(o interface{}) {
}
