
// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
// scene introduction = 数组->数组索引
// level = 3
// bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_no_solver/array_index_no_solver_001_T/array_index_no_solver_001_T
// evaluation information end

package main
import "os/exec"

func array_index_no_solver_001_T(__taint_src string) {
	var str = [3]string{__taint_src, "b", "c"}
	__taint_sink(str[0])
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    array_index_no_solver_001_T(__taint_src)
}