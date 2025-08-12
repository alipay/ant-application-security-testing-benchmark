
// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同结构体的不同字段
// scene introduction = 域敏感-路径长度
// level = 3
// bind_url = accuracy/field_sensitive/struct/field_len_002_F/field_len_002_F
// evaluation information end

package main
import "os/exec"

type A struct {
	data string
	sani string
}
type B struct {
	a A
}

type C struct {
	b B
}

func field_len_002_F(__taint_src string) {
	pa := A{
		data: __taint_src,
	}
	var b B
	b.a = pa
	var c C
	c.b = b
	__taint_sink(c.b.a.sani)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    field_len_002_F(__taint_src)
}