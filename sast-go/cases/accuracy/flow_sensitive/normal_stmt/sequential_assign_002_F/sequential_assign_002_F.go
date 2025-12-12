// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感分析->常规顺序执行语句
// scene introduction = 顺序赋值语句
// level = 2
// bind_url = accuracy/flow_sensitive/normal_stmt/sequential_assign_002_F/sequential_assign_002_F
// date = 2025-12-01 16:19:24
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

func sequentialAssign_002_F(__taint_src string) {
	// 场景特点：按顺序执行多个赋值语句，但污点数据未传播到最终变量
	var a string
	var b string
	a = __taint_src
	b = "_"
	_ = a // 使用变量a避免编译错误
	__taint_sink(b)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	sequentialAssign_002_F(__taint_src)
}
