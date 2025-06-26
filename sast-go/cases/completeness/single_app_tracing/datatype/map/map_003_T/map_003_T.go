
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字典
// scene introduction = 字典/映射（Map）对象
// level = 2
// bind_url = completeness/single_app_tracing/datatype/map/map_003_T/map_003_T
// evaluation information end

package map_003_T

func map_003_T(__taint_src string) {
	m := map[string]string{
		"key1": __taint_src,
	}
	__taint_sink(m)
}

func __taint_sink(o interface{}) {
}
