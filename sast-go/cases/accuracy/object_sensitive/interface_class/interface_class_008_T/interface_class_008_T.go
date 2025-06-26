
// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象
// scene introduction = 接口继承
// level = 2
// bind_url = accuracy/object_sensitive/interface_class/interface_class_008_T/interface_class_008_T
// evaluation information end

package interface_class_008_T

type ITestService interface {
	process(data string) string
}
type ITestService2 interface {
	ITestService
	process2(data string) string
}

type TestService struct {
}

func (c TestService) process(data string) string {
	return data
}

func (c TestService) process2(data string) string {
	return "_"
}

func interface_class_008_T(__taint_src string) {
	var iTestService ITestService2 = TestService{}
	res := iTestService.process(__taint_src)
	__taint_sink(res)
}

func __taint_sink(o interface{}) {
}
