
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->泛型
// scene introduction = 
// level = 2
// bind_url = completeness/single_app_tracing/datatype/generics/generics_002_F/generics_002_F
// evaluation information end

package generics_002_F

type Slice[T int | string | float64] []T

func generics_002_F(__taint_src string) {
	var s Slice[string] = []string{"a", "b", __taint_src}
	s[2] = "_"
	__taint_sink(s)
}

func __taint_sink(o interface{}) {
}
