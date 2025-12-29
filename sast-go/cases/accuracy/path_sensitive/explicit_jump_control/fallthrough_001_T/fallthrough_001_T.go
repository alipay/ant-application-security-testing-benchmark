package main
import "os/exec"


// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->跳转语句
// scene introduction = fallthrough
// level = 4
// bind_url = accuracy/path_sensitive/explicit_jump_control/fallthrough_001_T/fallthrough_001_T
// evaluation information end

func fallthrough_001_T(__taint_src string) {
	res := ""
	switch 1 {
	case 1:
		fallthrough
	case 2:
		res = __taint_src
	case 3:
		res = "_"
	default:
	}
	__taint_sink(res)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    fallthrough_001_T(__taint_src)
}