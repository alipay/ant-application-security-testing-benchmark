
// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同结构体
// scene introduction = 结构体对象
// level = 2
// bind_url = accuracy/object_sensitive/struct/struct_normal_002_F/struct_normal_002_F
// evaluation information end

package main
import "os/exec"

type A struct {
	data string
}

func struct_normal_002_F(__taint_src string) {
	p := A{
		data: __taint_src,
	}
	p2 := A{
		data: "_",
	}
	_ = p
	__taint_sink(p2)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    struct_normal_002_F(__taint_src)
}