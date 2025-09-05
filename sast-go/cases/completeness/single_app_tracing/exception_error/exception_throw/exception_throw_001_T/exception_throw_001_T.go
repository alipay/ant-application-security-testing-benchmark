package exception_throw_001_T


// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->异常与错误处理->异常抛出与捕获
// scene introduction = 异常抛出
// level = 2+
// bind_url = completeness/single_app_tracing/exception_error/exception_throw/exception_throw_001_T/exception_throw_001_T
// evaluation information end

func exception_throw_001_T(__taint_src string) {
	defer func() {
		if r := recover(); r != nil {
			__taint_sink(r)
		}
	}()

	panic(__taint_src)
}

func __taint_sink(o interface{}) {}
