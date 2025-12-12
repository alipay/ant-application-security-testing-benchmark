// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 识别导入根目录
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_030_F/cross/cross_01/cross_directory_030_F_a
// evaluation information end

package cross_directory_030_F_a

type Person struct {
	Name string
	Age  int
}

func (p Person) Skiing(__taint_src string) string{
	return __taint_src
}
