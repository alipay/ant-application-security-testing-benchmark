// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 多init函数顺序执行
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_020_F/cross/cross_init/cross_directory_init_020_F_b
// evaluation information end


package cross_init

var Status int = 0

func init() {
	Status += 3
}

func init() {
	Status += 4
}
