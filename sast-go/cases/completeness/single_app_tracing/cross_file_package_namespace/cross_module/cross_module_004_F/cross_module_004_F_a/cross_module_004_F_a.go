
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
// scene introduction = 跨module-别名
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_004_F/cross_module_004_F_a/cross_module_004_F_a
// evaluation information end

package cross_module_004_F_a
import "os/exec"

func Cross_module_004_F_a(o interface{}) {
	__taint_sink("_")
}
func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

