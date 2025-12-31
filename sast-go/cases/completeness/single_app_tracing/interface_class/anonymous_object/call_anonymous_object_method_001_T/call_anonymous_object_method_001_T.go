// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->匿名对象
// scene introduction = 调用匿名对象方法
// level = 2
// date = 2025-11-19 15:44:00
// bind_url = completeness/single_app_tracing/interface_class/anonymous_object/call_anonymous_object_method_001_T/call_anonymous_object_method_001_T
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

func call_anonymous_object_method_001_T(__taint_src string) {
	// 场景特点：匿名对象定义方法并调用返回污染数据
	obj := struct {
		getName func() string
	}{
		getName: func() string {
			return __taint_src
		},
	}

	// 场景特点：调用匿名对象的方法获取返回值
	result := obj.getName()
	taint_sink(result)
}

func taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	call_anonymous_object_method_001_T(__taint_src)
}
