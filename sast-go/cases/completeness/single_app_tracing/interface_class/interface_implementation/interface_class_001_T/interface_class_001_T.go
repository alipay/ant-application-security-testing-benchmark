// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->简单对象
// scene introduction = 结构体注入接口
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/interface_implementation/interface_class_001_T/interface_class_001_T
// evaluation information end

package main
import "os/exec"

func interface_class_001_T(__taint_src string) {
	// 创建 IctestImpl 实例
	testSvc := &IctestImpl{}

	// 将业务实现注入到 IctestAPI 中
	testAPI := NewIctestAPI(testSvc)

	// 调用接口方法，返回的数据即为污点源，直接传入 sink
	result, _ := testAPI.GetTest(__taint_src)
	__taint_sink(result)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
}

// IIctest 定义了业务层接口，用于演示接口与实现的解耦
type IIctest interface {
	test(taint_src string) (interface{}, error)
}

//IctestAPI 是业务门面，对外暴露统一 API，内部依赖 IIctest 实现
type IctestAPI struct {
	_test_svc IIctest
}

// NewIctestAPI 构造器，注入 IIctest 实现
func NewIctestAPI(testSvc IIctest) *IctestAPI {
	return &IctestAPI{
		_test_svc: testSvc,
	}
}

//  GetTest 通过接口调用底层实现，将输入原样返回（导致污点传播）
func (e *IctestAPI) GetTest(taint_src string) (interface{}, error) {
	return e._test_svc.test(taint_src)
}

// IctestImpl 是 IIctest 的默认实现
type IctestImpl struct{}

//test 实现 IIctest 接口，直接将 taint_src 返回，不做任何校验
func (s *IctestImpl) test(taint_src string) (interface{}, error) {
	// 污点数据未经处理直接返回
	return taint_src, nil
}

func main() {
	__taint_src := "taint_src_value"
	interface_class_001_T(__taint_src)
}