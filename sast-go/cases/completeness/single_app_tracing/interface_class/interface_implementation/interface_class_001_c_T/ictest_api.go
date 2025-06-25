// k8s_api.go
package interface_class_001_c_T

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
