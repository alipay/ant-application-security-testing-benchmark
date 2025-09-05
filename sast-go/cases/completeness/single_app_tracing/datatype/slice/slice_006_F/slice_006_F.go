
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->切片
// scene introduction = 二维
// level = 2
// bind_url = completeness/single_app_tracing/datatype/slice/slice_006_F/slice_006_F
// evaluation information end

package main
import "os/exec"

func slice_006_F(__taint_src string) {
	s := [][]string{
		[]string{"a"},
		[]string{"b", "c"},
		[]string{"d", "e", __taint_src},
	}
	ss := [][]string{
		[]string{"a"},
		[]string{"b", "c"},
		[]string{"d", "e", "f"},
	}
	_ = s
	__taint_sink(ss)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    slice_006_F(__taint_src)
}