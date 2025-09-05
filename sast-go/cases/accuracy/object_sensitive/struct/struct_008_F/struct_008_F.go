
// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同结构体
// scene introduction = 结构体对象
// level = 2
// bind_url = accuracy/object_sensitive/struct/struct_008_F/struct_008_F
// evaluation information end

package struct_008_F

type A struct {
	data string
}

func struct_008_F(__taint_src string) {
	p := A{
		data: __taint_src,
	}
	p2 := A{
		data: "_",
	}
	_ = p
	__taint_sink(p2)
}

func __taint_sink(o interface{}) {
}
