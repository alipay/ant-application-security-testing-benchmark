// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 多init函数顺序执行
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_019_T/cross/cross_directory_019_T
// evaluation information end
// 先cd到sast-go/cases/completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_019_T/cross
// 再执行go run cross_directory_019_T.go

package main
import (
	"cross_directory_019_T/cross/cross_init"
	"os/exec"
	"fmt"
)

// Go语言支持同一个包中有多个init函数，这些init可以在同一个文件也可以在不同文件中。
// 当这个包被import时，所有包中的init函数都会被执行
func cross_directory_019_T() {
	 // 若正确处理，pkg.Status的值应该是20
	__taint_sink(cross_init.Status)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
	}

func main() {
	cross_directory_019_T()
}