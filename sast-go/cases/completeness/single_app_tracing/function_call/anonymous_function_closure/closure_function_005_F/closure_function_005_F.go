
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
// scene introduction = 闭包->结构体
// level = 2
// bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/closure_function_005_F/closure_function_005_F
// evaluation information end

package closure_function_005_F

func closure_function_005_F(__taint_src string) {
	inner := func() *struct {
		update func() string
		get    func() string
	} {
		source := "_"
		return &struct {
			update func() string
			get    func() string
		}{
			update: func() string {
				source = __taint_src
				return source
			},
			get: func() string {
				return source
			},
		}
	}

	a := inner()
	__taint_sink(a.get())
}

func __taint_sink(o interface{}) {
}
