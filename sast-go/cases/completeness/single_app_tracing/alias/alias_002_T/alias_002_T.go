
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->别名
// scene introduction = 
// level = 2
// bind_url = completeness/single_app_tracing/alias/alias_002_T/alias_002_T
// evaluation information end

package alias_002_T

func alias_002_T(__taint_src string) {
	type Container struct {
		Value string
	}

	a := &Container{Value: "_"}
	b := a
	b.Value = __taint_src

	__taint_sink(a.Value)
}

func __taint_sink(o interface{}) {
}
