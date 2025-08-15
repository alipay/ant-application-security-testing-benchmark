package main
import "os/exec"


// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感分析->循环顺序执行语句
// scene introduction = for body
// level = 2
// bind_url = accuracy/flow_sensitive/loop_stmt/for_002_F/for_002_F
// evaluation information end

func for_002_F(__taint_src string) {
	res := ""
	for i := 0; i < 1; i++ {
		__taint_sink(res)
		res = __taint_src
	}
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    for_002_F(__taint_src)
}