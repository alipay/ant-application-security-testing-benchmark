
// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同结构体的不同字段
// scene introduction = 域敏感
// level = 3
// bind_url = accuracy/field_sensitive/struct/struct_field_004_F/struct_field_004_F
// evaluation information end

package main
import "os/exec"

type A struct {
	data string
	sani string
}

func struct_field_004_F(__taint_src string) {
	var p1 A
	p1.data = __taint_src
	p1.sani = "_"
	__taint_sink(p1.sani)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    struct_field_004_F(__taint_src)
}