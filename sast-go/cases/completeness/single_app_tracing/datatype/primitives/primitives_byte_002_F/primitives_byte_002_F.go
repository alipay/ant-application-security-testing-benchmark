// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
// scene introduction = 字节类型
// level = 2
// date = 2025-11-28 16:16:41
// bind_url = completeness/single_app_tracing/datatype/primitives/primitives_byte_002_F/primitives_byte_002_F
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

func primitives_byte_002_F(__taint_src byte) {
	// 场景特点：字节类型被净化
	var sani byte = __taint_src
	sani = byte(66)
	__taint_sink(sani)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := byte(65)
	primitives_byte_002_F(__taint_src)
}
