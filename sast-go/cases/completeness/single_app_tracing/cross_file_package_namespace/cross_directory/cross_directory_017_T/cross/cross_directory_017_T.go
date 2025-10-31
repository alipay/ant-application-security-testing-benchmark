// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 多init函数顺序执行
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_017_T/cross/cross_directory_017_T
// evaluation information end

// 先cd到sast-go/cases/completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_017_T/cross
// 再执行go run cross_directory_017_T.go

package main
import (
	"cross_directory_017_T/cross/cross_init"
	"os/exec"
)

// Go语言支持同一个包中有多个init函数，这些init可以在同一个文件也可以在不同文件中。
// init函数之间的执行是有顺序的，不同文件中则按文件排序顺序、同一文件则按init声明从上之下的顺序
// init函数是先执行的，所有init函数执行完后才会执行自定义函数
func cross_directory_017_T() {
	 // 若正确处理，Status的值应该是"taint_src_value234"
	__taint_sink(cross_init.Status)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
	cross_directory_017_T()
}
