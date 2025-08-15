package main
import "os/exec"


// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感分析->循环顺序执行语句
// scene introduction = for body
// level = 2
// bind_url = accuracy/flow_sensitive/loop_stmt/for_001_T/for_001_T
// evaluation information end

func for_001_T(__taint_src string) {
	res := ""
	for i := 0; i < 1; i++ {
		res = __taint_src
		__taint_sink(res)
	}
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    for_001_T(__taint_src)
}