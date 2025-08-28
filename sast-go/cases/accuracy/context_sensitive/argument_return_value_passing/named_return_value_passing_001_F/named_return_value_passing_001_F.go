// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 返回值传递->具名返回值
// level = 2 
// bind_url = accuracy/context_sensitive/argument_return_value_passing/named_return_value_passing_001_F/named_return_value_passing_001_F
// evaluation information end

package main
import "os/exec"

func named_return_value_passing_001_F(__taint_src interface{}) {
	a := "_"

	ret := processData(__taint_src, a)
	__taint_sink(ret)
}

func processData(s interface{}, i interface{}) (ret interface{}) {
	ret = i
	return
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    named_return_value_passing_001_F(__taint_src)
}