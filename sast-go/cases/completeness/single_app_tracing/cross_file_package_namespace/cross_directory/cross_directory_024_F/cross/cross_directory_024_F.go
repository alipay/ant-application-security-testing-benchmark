// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 可见性校验
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_024_F/cross/cross_directory_024_F
// evaluation information end


// 先cd sast-go/cases/completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_024_F
// 再执行 go run cross/cross_directory_024_F.go

package main
import (
	"fmt"
	"cross_directory_024_F/cross/cross_01"
	"os/exec"
)

// Go语言中，一个包内只有大写开头的Symbol能够被导出(对外部可见)
// 考察特性：YASA是否会错误地将小写的(非public的)Symbol错误的import过来

func cross_directory_024_F() {
	__taint_sink(cross_01.status) //status小写 若正确处理，无法获取到cross_01.status
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
	}

func main() {
	cross_directory_024_F()
}
