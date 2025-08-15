package main
import "os/exec"


// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感分析->跳转语句
// scene introduction = continue
// level = 4+
// bind_url = accuracy/path_sensitive/explicit_jump_control/continue_002_F/continue_002_F
// evaluation information end

func continue_002_F(__taint_src string) {
	res := ""
	for i := 0; i < 10; i++ {
		if i == 3 {
			res = __taint_src
			continue
			__taint_sink(res)
		}
	}
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    continue_002_F(__taint_src)
}