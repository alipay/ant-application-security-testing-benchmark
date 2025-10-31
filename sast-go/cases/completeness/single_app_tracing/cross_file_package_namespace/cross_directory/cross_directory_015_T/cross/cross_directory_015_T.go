// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = init函数自动执行
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_015_T/cross/cross_directory_015_T
// evaluation information end

// 先cd到sast-go/cases/completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_015_T/cross
// 再执行go run cross_directory_015_T.go
package main
import (
	"cross_directory_015_T/cross/cross_init"
	"os/exec"
	"fmt"
)

// Go语言支持包中定义init函数，在这个包被首次初始化(import)时，会自动触发这个包的init函数
func cross_directory_015_T() {
	 // 看cross_init.Status是否被init处理过
	__taint_sink(cross_init.Status)
}

func __taint_sink(o interface{}) {
	fmt.Println("o 的值:", o)
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    cross_directory_015_T()
}