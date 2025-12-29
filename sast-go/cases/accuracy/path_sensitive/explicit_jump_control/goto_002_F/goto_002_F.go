package main
import "os/exec"
// evaluation information start
// real case = false
// evaluation item =准确度->路径敏感分析->跳转语句
// scene introduction = goto
// level = 4
// bind_url = accuracy/path_sensitive/explicit_jump_control/goto_002_F/goto_002_F
// evaluation information end

func goto_002_F(__taint_src string) {
	res := __taint_src

	goto Sanitizer

Sanitizer:
	res = "_"
	goto Sink

Sink:
	__taint_sink(res)
	goto End

End:
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    goto_002_F(__taint_src)
}