// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感分析->异常抛出和捕获
// scene introduction = 异常抛出路径
// level = 3
// bind_url = accuracy/path_sensitive/exception_throw/exception_catch_002_F/exception_catch_002_F
// date = 2025-12-01 16:29:18
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

func exception_catch_002_F(__taint_src string) {
	// 场景特点：在异常抛出路径中传播污点数据，但污点数据未传播到汇聚点
	defer func() {
		if r := recover(); r != nil {
			__taint_sink("_")
		}
	}()

	panic(__taint_src)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	exception_catch_002_F(__taint_src)
}
