// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 导入路径与包名解耦
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_026_F/cross/cross_01/cross_directory_026_F_a
// evaluation information end


package cross_directory_026_F_a

var status string

type Person struct {
	Name string
	Age  int
}

func (p Person) Swimming(taint_src string) string {  
	status = taint_src
	return status 
}

