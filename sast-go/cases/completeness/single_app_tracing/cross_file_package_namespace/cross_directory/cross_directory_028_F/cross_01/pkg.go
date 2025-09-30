// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 同名包路径区分
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_028_F/cross_01/pkg
// evaluation information end


package pkg
import "os/exec"

var dir string

func Fun(__taint_src string) {
	dir = __taint_src
	__taint_sink(dir)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

