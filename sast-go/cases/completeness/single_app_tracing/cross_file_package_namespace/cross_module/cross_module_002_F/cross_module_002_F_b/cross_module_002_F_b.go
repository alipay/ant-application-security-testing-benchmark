
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
// scene introduction = 跨module
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_002_F/cross_module_002_F_b/cross_module_002_F_b
// evaluation information end



//Go 使用 go.mod 文件来标识一个模块的根目录。当运行 go run .、go build 或 go mod tidy 等命令时：
//Go 会从当前目录向上查找，直到找到 go.mod 文件
//然后以该 go.mod 所在目录为模块根目录
//所有依赖、导入路径、replace 指令等都基于这个模块根目录解析
//在执行时先cd到sast-go/cases/completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_002_F/cross_module_002_F_b
//再执行 go run cross_module_002_F_b.go


package main

import (
	"gotest.com/cross_module_002_F_a"
)

func cross_module_002_F_b(__taint_src string) {
	cross_module_002_F_a.Cross_module_002_F_a("aa")
}

func main() {
    __taint_src := "taint_src_value"
    cross_module_002_F_b(__taint_src)
}