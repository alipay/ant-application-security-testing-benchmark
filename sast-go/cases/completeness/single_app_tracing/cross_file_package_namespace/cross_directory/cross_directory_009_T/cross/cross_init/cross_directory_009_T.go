// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 跨package5
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_009_T/cross/ccross_init/cross_directory_009_T
// evaluation information end

package pkg

var Arg = "12323"

//	func SetArg(newVal string) {
//		arg = newVal
//	}
func init() {
	Arg = "_"
}

func Cross_directory_009_T(o interface{}) {
	__taint_sink(Arg)
}

func __taint_sink(o interface{}) {}
