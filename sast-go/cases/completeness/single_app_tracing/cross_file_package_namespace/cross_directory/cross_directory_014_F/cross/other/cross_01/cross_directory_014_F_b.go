// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = replace包层级调用链
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_014_F/cross/other/cross_01/cross_directory_014_F_b
// evaluation information end

package cross_directory_014_F_b
import "os/exec"

func Cross_directory_014_F_b(taint_src string) {
	__taint_sink("_")
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}