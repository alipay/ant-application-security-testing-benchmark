
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->返回值传递
// scene introduction = 多返回值传递给结构体 
// level = 2
// bind_url = completeness/single_app_tracing/function_call/return_value_passing/multiple_return_struct_002_T/multiple_return_struct_002_T
// evaluation information end

package main
import (
	"fmt"
	"os/exec"
	"database/sql"
)
// req.prop, _ = c.Cookie() uast4Go会将这句翻译成variableDecl，导致taint无法写入到req对象中

type Request struct {
	Name string
	prop sql.DB
}

func multiple_return_struct_002_T(__taint_src sql.DB) {
	req := Request{}

	req.prop, _ = processData(__taint_src, "_")

	__taint_sink(req)
}

func processData(s sql.DB, i string) (sql.DB, string) {
	return s , i
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%+v", o)).Run()
	}

func main() {
	  var __taint_src sql.DB
    multiple_return_struct_002_T(__taint_src)
}