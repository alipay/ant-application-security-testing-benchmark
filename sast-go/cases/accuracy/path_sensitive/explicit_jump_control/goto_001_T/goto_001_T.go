package main
import "os/exec"
// evaluation information start
// real case = true
// evaluation item =准确度->路径敏感分析->跳转语句
// scene introduction = goto
// level = 4
// bind_url = accuracy/path_sensitive/explicit_jump_control/goto_001_T/goto_001_T
// evaluation information end

func goto_001_T(__taint_src string) {
	res := __taint_src

	goto Sink

Sanitizer:
	res = "_"
	goto End

Sink:
	__taint_sink(res)
	goto Sanitizer

End:
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    goto_001_T(__taint_src)
}