// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->异常与错误处理->异常抛出与捕获
// scene introduction = 异常抛出->自定义异常抛出
// level = 2+
// date = 2025-11-27 10:52:11
// bind_url = completeness/single_app_tracing/exception_error/exception_throw/exception_throw_004_F/exception_throw_004_F
// evaluation information end

package main

import (
	"os/exec"
)

// 场景特点：定义自定义异常类型
type CustomError struct {
	message string
}

func (e *CustomError) Error() string {
	return e.message
}

func exception_throw_004_T(__taint_src string) {
	defer func() {
		if r := recover(); r != nil {
			defer func() {
				if r := recover(); r != nil {
					var cmdStr string = r.(*CustomError).message
					__taint_sink(cmdStr)
				}
			}()
		}
	}()

	throwCustomError(__taint_src)
}

func throwCustomError(__taint_src string) {
	// 场景特点：抛出不相关的自定义异常
	panic(&CustomError{message: "unrelated_value"})
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	exception_throw_004_T(__taint_src)
}
