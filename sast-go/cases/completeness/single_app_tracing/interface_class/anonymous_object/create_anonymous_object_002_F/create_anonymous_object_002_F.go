// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->接口与类->匿名对象
// scene introduction = 创建匿名对象
// level = 2
// date = 2025-11-19 15:38:00
// bind_url = completeness/single_app_tracing/interface_class/anonymous_object/create_anonymous_object_002_F/create_anonymous_object_002_F
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

func create_anonymous_object_002_F(__taint_src string) {
	// 场景特点：使用结构体字面量创建匿名对象但使用安全值
	person := struct {
		name string
	}{
		name: "safe_value",
	}
	taint_sink(person.name)
}

func taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	create_anonymous_object_002_F(__taint_src)
}
