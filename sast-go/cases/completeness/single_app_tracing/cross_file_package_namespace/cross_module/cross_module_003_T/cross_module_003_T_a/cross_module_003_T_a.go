
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
// scene introduction = 跨module-别名
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_003_T/cross_module_003_T_a/cross_module_003_T_a
// evaluation information end

package cross_module_003_T_a
import "os/exec"

func Cross_module_003_T_a(o interface{}) {
	__taint_sink(o)
}
func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

