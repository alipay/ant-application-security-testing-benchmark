// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->简单对象
// scene introduction = 结构体注入接口
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/interface_implementation/interface_class_002_c_F/interface_class_002_c_F

// evaluation information end

package main
import "os/exec"

func interface_class_002_c_F(__taint_src string) {
	// 创建接口的具体实现
	testSvc := &IctestImpl{}

	// 通过构造函数注入接口
	testAPI := NewIctestAPI(testSvc)

	// 调用接口方法
	result, _ := testAPI.GetTest("")
	__taint_sink(result)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
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
    _ = exec.Command("sh", "-c", o.(string)).Run()
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

func main() {
    __taint_src := "taint_src_value"
    interface_class_002_c_F(__taint_src)
}