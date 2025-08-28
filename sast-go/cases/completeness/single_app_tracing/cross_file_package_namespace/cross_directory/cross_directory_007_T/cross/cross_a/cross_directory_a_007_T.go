// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 跨package
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_007_T/cross/cross_a/cross_directory_a_007_T
// evaluation information end

package pkga
import "os/exec"

func Cross_directory_a_007_T(o interface{}) {
	__taint_sink(o)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

