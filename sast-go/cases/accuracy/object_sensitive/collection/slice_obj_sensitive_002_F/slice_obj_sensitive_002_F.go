
// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
// scene introduction = 切片->一维切片
// level = 2
// bind_url = accuracy/object_sensitive/collection/slice_obj_sensitive_002_F/slice_obj_sensitive_002_F
// evaluation information end

package main
import (
	"os/exec"
	"fmt"
)

func slice_obj_sensitive_002_F(__taint_src string) {
	var arr [3]string = [3]string{__taint_src, "b", "c"}
	var arr2 [3]string = [3]string{"a", "b", "c"}
	var s []string
	_ = arr
	s = arr2[:]
	__taint_sink(s)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    slice_obj_sensitive_002_F(__taint_src)
}