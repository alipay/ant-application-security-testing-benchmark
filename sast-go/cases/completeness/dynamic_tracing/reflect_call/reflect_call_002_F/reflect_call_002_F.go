
// evaluation information start
// real case = true
// evaluation item = 完整度->动态特性跟踪完整度->反射调用
// scene introduction = 
// level = 3
// bind_url = completeness/dynamic_tracing/reflect_call/reflect_call_002_F/reflect_call_002_F
// evaluation information end

package main

import (
	"os/exec"
	"reflect"
	"fmt"
)

type T struct {
	A int
	B string
}

func reflect_call_002_F(__taint_src string) {
	t := T{123, "_"}
	s := reflect.ValueOf(t)

	for i := 0; i < s.NumField(); i++ {
		__taint_sink(s.Field(i))
	}
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    reflect_call_002_F(__taint_src)
}