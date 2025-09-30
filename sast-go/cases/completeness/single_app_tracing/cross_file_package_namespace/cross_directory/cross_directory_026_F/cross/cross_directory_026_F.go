// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 导入路径与包名解耦
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_026_F/cross/cross_directory_026_F
// evaluation information end

// 先cd sast-go/cases/completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_026_F
// 再执行 go run cross/cross_directory_026_F.go
package main
import (
	"fmt"
	"cross_directory_026_F/cross/cross_01"
	"os/exec"
)

// Go语言中，import路径从第二项开始的每项一定是目录名，包括最后一项(并非包名)。
// 然而，导入后，使用的符号值是包名。比如这边，import cross_01，使用的却是pkg

func cross_directory_026_F(__taint_src string) {
	__taint_sink(cross_directory_026_F_a.Person{}.Swimming("_")) 
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
	}

func main() {
	__taint_src := "taint_src_value"
	cross_directory_026_F(__taint_src)
}
