
// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
// scene introduction = 数组->复合数据类型
// level = 2
// bind_url = accuracy/object_sensitive/collection/array_obj_sensitive_001_T/array_obj_sensitive_001_T
// evaluation information end

package main
import (
	"os/exec"
	"fmt"
)

func array_obj_sensitive_001_T(__taint_src string) {
	var str = [3]string{__taint_src, "b", "c"}
	var str2 = [3]string{"a", "b", "c"}
	_ = str2
	__taint_sink(str)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    array_obj_sensitive_001_T(__taint_src)
}