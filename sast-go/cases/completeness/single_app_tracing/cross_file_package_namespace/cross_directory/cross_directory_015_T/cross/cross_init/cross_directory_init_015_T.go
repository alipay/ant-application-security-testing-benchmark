// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = init函数自动执行
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_015_T/cross/cross_init/cross_directory_init_015_T
// evaluation information end

package cross_init

var Status string

func init() {
	Status = "taint_src_value"
}
