// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->返回值传递
// scene introduction = 条件返回nil 
// level = 2
// bind_url = completeness/single_app_tracing/function_call/return_value_passing/if_return_nil_002_F/if_return_nil_002_F
// evaluation information end

package main
import (
	"fmt"
	"os/exec"
)


type S struct {
	name string
	id   int
}

func Func1(__taint_src string) (*S, string) {
	s1 := &S{
		name: __taint_src,
		id:   98,
	}
	err := "abc"

	if err != "nil" {
		return nil, err
	}
	
	return s1, "abc"
}

func if_return_nil_002_F(__taint_src string) {
	res, _ := Func1(__taint_src)
	__taint_sink(res)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%+v", o)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    if_return_nil_002_F(__taint_src)
}