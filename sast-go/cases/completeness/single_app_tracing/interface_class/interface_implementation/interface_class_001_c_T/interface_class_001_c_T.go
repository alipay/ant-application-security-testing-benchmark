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

// IHarborService 接口定义 GetImage 方法
type IIctest interface {
	test(taint_src string) (interface{}, error)
}

// K8sAPI 结构体，依赖 IHarborService 接口
type IctestAPI struct {
	_test_svc IIctest
}

// NewK8sAPI 构造函数，注入 IHarborService 接口
func NewIctestAPI(testSvc IIctest) *IctestAPI {
	return &IctestAPI{
		_test_svc: testSvc,
	}
}

// GetHarborImage 方法，调用接口的 GetImage 方法
func (e *IctestAPI) GetTest(taint_src string) (interface{}, error) {
	return e._test_svc.test(taint_src)
}

// HarborServiceImpl 是 IHarborService 接口的具体实现
type IctestImpl struct{}

// 实现 GetImage 方法
func (s *IctestImpl) test(taint_src string) (interface{}, error) {
	// 模拟返回一个简单结果
	return taint_src, nil
}
