// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 同名包导入区分
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_022_F/cross/cross_same_name_022_F
// evaluation information end

package cross_same_name_022_F
import "os/exec"

func SayHello(taint_src string) {
	__taint_sink(taint_src)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

	