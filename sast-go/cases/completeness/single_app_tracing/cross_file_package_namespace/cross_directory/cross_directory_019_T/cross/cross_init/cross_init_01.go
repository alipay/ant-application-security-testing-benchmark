// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 多init函数顺序执行
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_019_T/cross/cross_init/cross_init_01
// evaluation information end

package cross_init


func init() {
	Status += 1
}

func init() {
	Status += 2
}


func In_init_after(taint_src int) {
	Status += taint_src
}
