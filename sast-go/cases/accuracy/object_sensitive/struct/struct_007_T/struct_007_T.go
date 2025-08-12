
// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同结构体
// scene introduction = 结构体对象
// level = 2
// bind_url = accuracy/object_sensitive/struct/struct_007_T/struct_007_T
// evaluation information end

package main
import "os/exec"

type A struct {
	data string
}

func struct_007_T(__taint_src string) {
	p := A{
		data: __taint_src,
	}
	p2 := A{
		data: "_",
	}
	_ = p2
	__taint_sink(p)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    struct_007_T(__taint_src)
}