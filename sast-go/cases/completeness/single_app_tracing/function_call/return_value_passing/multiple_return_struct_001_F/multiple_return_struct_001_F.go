
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->返回值传递
// scene introduction = 多返回值传递给结构体 
// level = 2
// bind_url = completeness/single_app_tracing/function_call/return_value_passing/multiple_return_struct_001_F/multiple_return_struct_001_F
// evaluation information end

package main
import (
	"fmt"
	"os/exec"
	"database/sql"
)

type Request struct {
	Name string
	prop sql.DB
}

func multiple_return_struct_001_F(__taint_src string) {
	req := Request{}

	req.prop, _ = processData(__taint_src, "_")

	__taint_sink(req)
}

func processData(s string, i string) (sql.DB, string) {
	var db sql.DB
	return db , i
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%+v", o)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    multiple_return_struct_001_F(__taint_src)
}