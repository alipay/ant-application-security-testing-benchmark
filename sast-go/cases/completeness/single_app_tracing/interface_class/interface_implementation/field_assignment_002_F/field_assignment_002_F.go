// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->接口与类->接口的实现
// scene introduction = 接口字段赋值
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/interface_implementation/field_assignment_002_F/field_assignment_002_F
// evaluation information end

package main

import "os/exec"

func interface_field_assignment_012_F(__taint_src string) {
	// 场景特点：将接口类型字段赋值为实现类实例
	container := &Container{}
	testImpl := &IctestImpl{}
	container.testInterface = testImpl

	// 通过结构体字段调用接口方法，但传入固定字符串而非污点数据
	result, _ := container.testInterface.test("safe_string")
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

// test 实现接口方法，直接返回传入数据
func (s *IctestImpl) test(taint_src string) (interface{}, error) {
	return taint_src, nil
}

// Container 包含接口类型字段
type Container struct {
	testInterface IIctest
}

func main() {
	__taint_src := "taint_src_value"
	interface_field_assignment_012_F(__taint_src)
}
