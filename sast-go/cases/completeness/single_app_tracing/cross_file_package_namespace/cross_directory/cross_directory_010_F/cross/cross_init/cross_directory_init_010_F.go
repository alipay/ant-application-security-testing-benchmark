// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 跨package
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_010_F/cross/cross_init/cross_directory_init_010_F
// evaluation information end

package pkg
import "os/exec"


var Arg = "12323"

func init() {
	Arg = "_"
}

func cross_directory_init_010_F(o interface{}) {
	__taint_sink(Arg)
}


func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}


