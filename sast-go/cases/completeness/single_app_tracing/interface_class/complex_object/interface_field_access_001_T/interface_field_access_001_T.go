// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
// scene introduction = 接口类型字段访问
// level = 2
// date = 2025-11-17 14:36:00
// bind_url = completeness/single_app_tracing/interface_class/complex_object/interface_field_access_001_T/interface_field_access_001_T
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

type DataHolder interface {
	GetData() string
}

type MyData struct {
	data string
}

func (m MyData) GetData() string {
	return m.data
}

type Container struct {
	holder DataHolder
}

func interface_field_access_001_T(__taint_src string) {
	// 场景特点：通过接口类型访问底层结构体字段
	data := MyData{data: __taint_src}
	container := Container{holder: data}
	taint_sink(container.holder.GetData())
}

func taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	interface_field_access_001_T(__taint_src)
}
