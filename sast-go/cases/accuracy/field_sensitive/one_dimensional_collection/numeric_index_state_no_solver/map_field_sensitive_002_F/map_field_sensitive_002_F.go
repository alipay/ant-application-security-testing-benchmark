
// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
// scene introduction = map->字典/映射（Map）-域敏感
// level = 3
// bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_no_solver/map_field_sensitive_002_F/map_field_sensitive_002_F
// evaluation information end

package main
import "os/exec"

func map_field_sensitive_002_F(__taint_src string) {
	m := make(map[string]string, 2)
	m["key1"] = __taint_src
	m["key2"] = "value"
	__taint_sink(m["key2"])
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    map_field_sensitive_002_F(__taint_src)
}