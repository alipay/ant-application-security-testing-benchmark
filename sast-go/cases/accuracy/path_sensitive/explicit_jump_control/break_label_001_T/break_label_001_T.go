package main
import "os/exec"


// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->跳转语句
// scene introduction = break_label
// level = 4
// bind_url = accuracy/path_sensitive/explicit_jump_control/break_label_001_T/break_label_001_T
// evaluation information end

func break_label_001_T(__taint_src string) {
	res := ""

myLabel:
	for i := 0; i < 2; i++ {
		res = __taint_src
		break myLabel
	}

	__taint_sink(res)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    break_label_001_T(__taint_src)
}