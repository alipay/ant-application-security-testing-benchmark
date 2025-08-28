
// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分多维字典/列表/数组的不同元素
// scene introduction = 切片索引-二维
// level = 4
// bind_url = accuracy/field_sensitive/multidimensional_collection/slice_index_mc_001_T/slice_index_mc_001_T
// evaluation information end

package main
import "os/exec"

func slice_index_mc_001_T(__taint_src string) {
	s := [][]string{
		[]string{"a"},
		[]string{"b", "c"},
		[]string{"d", "e", __taint_src},
	}
	__taint_sink(s[2][2])
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    slice_index_mc_001_T(__taint_src)
}