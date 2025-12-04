// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->异常抛出和捕获
// scene introduction = 异常抛出路径
// level = 3
// bind_url = accuracy/path_sensitive/exception_throw/exception_catch_001_T/exception_catch_001_T
// date = 2025-12-01 16:29:18
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

func exception_catch_001_T(__taint_src string) {
	// 场景特点：在异常抛出路径中传播污点数据
	defer func() {
		// recover只能在defer函数中调用，并捕获最新一次panic的值
		if r := recover(); r != nil {
			__taint_sink(r)
		}
	}()

	// 立即平直当前函数，依次执行 defer 函数
	panic(__taint_src)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	exception_catch_001_T(__taint_src)
}
