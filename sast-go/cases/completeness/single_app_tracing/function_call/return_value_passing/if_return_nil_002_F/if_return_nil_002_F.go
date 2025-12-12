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

// 旧版中，对nil没有进行处理限制，允许将nil值转换成返回值类型(S)，且允许对nil进行memberAccess读取

type S struct {
	name string
	id   int
}

func Func1(__taint_src string) (*S) {
	s1 := &S{
		name: __taint_src,
		id:   98,
	}
	err := "error"

	if err != "nil" {
		return nil
	}
	
	return s1
}

func if_return_nil_002_F(__taint_src string) {
	res := Func1(__taint_src)
	__taint_sink(res)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%+v", o)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    if_return_nil_002_F(__taint_src)
}