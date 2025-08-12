package main
import "os/exec"


// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感分析->循环顺序执行语句
// scene introduction = for init
// level = 2
// bind_url = accuracy/flow_sensitive/loop_stmt/for_004_F/for_004_F
// evaluation information end

func for_004_F(__taint_src string) {
	res := ""
	i := 0
	for __taint_sink(res); i < 1; i++ {
		res = __taint_src
	}
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    for_004_F(__taint_src)
}