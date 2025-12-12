package main
import "os/exec"


// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->跳转语句
// scene introduction = return
// level = 4+
// bind_url = accuracy/path_sensitive/explicit_jump_control/return_001_T/return_001_T
// evaluation information end

func return_001_T(__taint_src string) string {
	if false {
		return __taint_src
	}
	__taint_sink(__taint_src)
	return ""
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    return_001_T(__taint_src)
}