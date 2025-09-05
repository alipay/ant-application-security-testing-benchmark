
// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同结构体
// scene introduction = 结构体对象->3层对象
// level = 2
// bind_url = accuracy/object_sensitive/struct/struct_deep3_003_T/struct_deep3_003_T
// evaluation information end

package struct_deep3_003_T

type DeepC01 struct {
	deepC02 DeepC02
}

type DeepC02 struct {
	deepC03 DeepC03
}

type DeepC03 struct {
	deepC04 string
}

func struct_deep3_003_T(__taint_src string) {
	//创建第一个 DeepC01 实例，并为其嵌套字段赋值
	obj1 := DeepC01{}
	//创建第二个 DeepC01 实例
	//obj2 := DeepC01{}
	deepC03 := DeepC03{
		deepC04: __taint_src,
	}
	obj1.deepC02.deepC03 = deepC03

	__taint_sink(obj1)
}

func __taint_sink(o interface{}) {
	//模拟污点数据的汇聚点
}
