// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 跨package
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_003_T/cross/cross_01/cross_02/cross_directory_003_T_a
// evaluation information end

package cross_pkg_deep2

func Cross_directory_003_T_a(o interface{}) {
	__taint_sink(o)
}

func __taint_sink(o interface{}) {}
