
// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同类对象
// scene introduction = 
// level = 2
// bind_url = accuracy/object_sensitive/interface_class/interface_class_004_T/interface_class_004_T
// evaluation information end

package main
import "os/exec"

type ITestService2 interface {
	process(data string) string
	process2(data string) string
}

type ITestService interface {
	process(data string) string
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

type TestService2 struct {
}

func (c TestService2) process(data string) string {
	return "_"
}

func (c TestService2) process2(data string) string {
	return "_"
}

func interface_class_004_T(__taint_src string) {
	var iTestService ITestService = TestService{}
	res := iTestService.process(__taint_src)
	__taint_sink(res)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    interface_class_004_T(__taint_src)
}