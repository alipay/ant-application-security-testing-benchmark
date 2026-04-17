// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感分析->异常抛出和捕获
// scene introduction = 异常抛出路径
// level = 3
// date = 2025-12-01 16:29:18
// bind_url = accuracy/path_sensitive/exception_throw/exception_catch_004_F/exception_catch_004_F
// evaluation information end

package main

import (
	"errors"
	"fmt"
	"os/exec"
)

func exception_catch_004_F(__taint_src string) {
	// 场景特点：在异常抛出路径中传播污点数据
	errMsg := createThrow(__taint_src)
	__taint_sink(errMsg.Error())
}

func createThrow(msg string) error {
	return errors.New("_")
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	exception_catch_004_F(__taint_src)
}
