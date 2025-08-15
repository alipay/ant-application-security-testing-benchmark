
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->切片
// scene introduction = 二维
// level = 2
// bind_url = completeness/single_app_tracing/datatype/slice/slice_005_T/slice_005_T
// evaluation information end

package main
import "os/exec"

func slice_005_T(__taint_src string) {
	s := [][]string{
		[]string{"a"},
		[]string{"b", "c"},
		[]string{"d", "e", __taint_src},
	}
	__taint_sink(s)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    slice_005_T(__taint_src)
}