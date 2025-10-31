// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = init函数自动执行
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_016_F/cross/cross_directory_016_F
// evaluation information end
// 先cd到sast-go/cases/completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_016_F/cross
// 再执行go run cross_directory_016_F.go
package main
import (
	"cross_directory_016_F/cross/cross_init"
	"os/exec"
)
func cross_directory_016_F() {
	__taint_sink(cross_init.Status)
}
func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}
func main() {
	cross_directory_016_F()
}