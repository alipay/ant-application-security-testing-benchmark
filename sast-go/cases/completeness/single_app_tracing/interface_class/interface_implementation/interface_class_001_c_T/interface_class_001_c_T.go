// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->简单对象
// scene introduction = 结构体注入接口
// level = 2
// bind_url = accuracy/object_sensitive/interface_class/interface_class_001_c_T/interface_class_001_c_T
// evaluation information end

package interface_class_001_c_T

func interface_class_001_c_T(__taint_src string) {
	// 创建接口的具体实现
	testSvc := &IctestImpl{}

	// 通过构造函数注入接口
	testAPI := NewIctestAPI(testSvc)

	// 调用接口方法
	result, _ := testAPI.GetTest(__taint_src)
	__taint_sink(result)
}

func __taint_sink(o interface{}) {
}
