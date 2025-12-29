// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->异常与错误处理->异常抛出与捕获
// scene introduction = 异常抛出->函数内抛出
// level = 2
// bind_url = completeness/single_app_tracing/exception_error/exception_throw/exception_throw_002_F/exception_throw_002_F
// evaluation information end

package main

import "os/exec"

func exception_throw_002_F(__taint_src string) {
	defer func() {
		if r := recover(); r != nil {
			__taint_sink(r)
		}
	}()

	panic("_")

}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	exception_throw_002_F(__taint_src)
}
