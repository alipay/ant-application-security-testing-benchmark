
// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
// scene introduction = 切片->一维切片
// level = 2
// bind_url = accuracy/object_sensitive/collection/slice_obj_sensitive_001_T/slice_obj_sensitive_001_T
// evaluation information end

package main
import "os/exec"

func slice_001slice_obj_sensitive_001_T_T(__taint_src string) {
	var arr [3]string = [3]string{__taint_src, "b", "c"}
	var arr2 [3]string = [3]string{"a", "b", "c"}
	var s []string
	_ = arr2
	s = arr[:]
	__taint_sink(s)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    slice_obj_sensitive_001_T(__taint_src)
}