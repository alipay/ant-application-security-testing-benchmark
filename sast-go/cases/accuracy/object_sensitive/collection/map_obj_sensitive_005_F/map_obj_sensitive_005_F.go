
// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
// scene introduction = map->映射
// level = 2
// bind_url = accuracy/object_sensitive/collection/map_obj_sensitive_005_F/map_obj_sensitive_005_F
// evaluation information end

package main
import "os/exec"

func map_obj_sensitive_005_F(__taint_src string) {
	m := map[string]string{
		"key1": __taint_src,
	}
	n := map[string]string{
		"key1": "_",
	}
	_ = m
	__taint_sink(n)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    map_obj_sensitive_005_F(__taint_src)
}