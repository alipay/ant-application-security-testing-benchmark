
// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
// scene introduction = 切片->二维切片
// level = 2
// bind_url = accuracy/object_sensitive/collection/slice_obj_sensitive_003_T/slice_obj_sensitive_003_T
// evaluation information end

package main
import "os/exec"

func slice_obj_sensitive_003_T(__taint_src string) {
	s := [][]string{
		[]string{"a"},
		[]string{"b", "c"},
		[]string{"d", "e", __taint_src},
	}
	s2 := [][]string{
		[]string{"a"},
		[]string{"b", "c"},
		[]string{"d", "e", "f"},
	}
	_ = s2
	__taint_sink(s)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    slice_obj_sensitive_003_T(__taint_src)
}