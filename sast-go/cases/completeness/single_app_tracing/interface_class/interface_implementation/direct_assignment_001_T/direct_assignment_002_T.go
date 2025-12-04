// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->接口的实现
// scene introduction = 接口直接赋值
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/interface_implementation/direct_assignment_001_T/direct_assignment_002_T
// evaluation information end

package main

import "os/exec"

func interface_direct_assignment_003_T(__taint_src string) {
	// 场景特点：接口类型变量直接赋值为实现类实例
	var testInterface IIctest
	testImpl := &IctestImpl{}
	testInterface = testImpl

	// 通过接口调用方法，污点数据直接传递
	result, _ := testInterface.test(__taint_src)
	__taint_sink(result)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
}

// IIctest 定义了测试接口
type IIctest interface {
	test(taint_src string) (interface{}, error)
}

// IctestImpl 是 IIctest 的实现
type IctestImpl struct{}

// test 实现接口方法，直接返回污点数据
func (s *IctestImpl) test(taint_src string) (interface{}, error) {
	return taint_src, nil
}

func main() {
	__taint_src := "taint_src_value"
	interface_direct_assignment_003_T(__taint_src)
}
