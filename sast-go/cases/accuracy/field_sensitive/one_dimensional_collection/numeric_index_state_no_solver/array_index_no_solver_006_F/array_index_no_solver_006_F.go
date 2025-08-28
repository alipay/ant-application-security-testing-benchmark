
// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
// scene introduction = 数组->数组赋值
// level = 3
// bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_no_solver/array_index_no_solver_006_F/array_index_no_solver_006_F
// evaluation information end

package main
import "os/exec"

func array_index_no_solver_006_F(__taint_src string) {
	var arr [3]string
	var symbol int = 0
	arr[symbol] = __taint_src
	symbol = 1
	arr[symbol] = "benign"

	__taint_sink(arr[1])
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}
func main() {
    __taint_src := "taint_src_value"
    array_index_no_solver_006_F(__taint_src)
}