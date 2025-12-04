// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
// scene introduction = 多层嵌套字段写入
// level = 2+
// date = 2025-11-17 14:33:00
// bind_url = completeness/single_app_tracing/interface_class/complex_object/deep_nested_field_write_001_T/deep_nested_field_write_001_T
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

type Street struct {
	name string
	no   int
}

type Address struct {
	city   string
	street Street
}

type Person struct {
	name    string
	address Address
}

type Company struct {
	name    string
	manager Person
}

func deep_nested_field_write_001_T(__taint_src string) {
	// 场景特点：向四层嵌套结构体的最深层字段赋值
	var comp Company
	comp.name = "TechCorp"
	comp.manager.name = "John"
	comp.manager.address.city = "Beijing"
	comp.manager.address.street.name = __taint_src
	comp.manager.address.street.no = 100

	taint_sink(comp.manager.address.street.name)
}

func taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	deep_nested_field_write_001_T(__taint_src)
}
