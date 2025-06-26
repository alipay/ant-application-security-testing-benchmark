
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->别名
// scene introduction = 
// level = 2
// bind_url = completeness/single_app_tracing/alias/alias_001_F/alias_001_F
// evaluation information end

package alias_001_F

func alias_001_F(__taint_src string) {
	type Container struct {
		Value string
	}

	a := &Container{Value: __taint_src}
	b := a
	b.Value = "_"

	__taint_sink(a.Value)
}

func __taint_sink(o interface{}) {
}
