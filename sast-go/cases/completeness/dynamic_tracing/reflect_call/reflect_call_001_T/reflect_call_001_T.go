
// evaluation information start
// real case = true
// evaluation item = 完整度->动态特性跟踪完整度->反射调用
// scene introduction = 
// level = 3
// bind_url = completeness/dynamic_tracing/reflect_call/reflect_call_001_T/reflect_call_001_T
// evaluation information end

package reflect_call_001_T

import (
	"reflect"
)

type T struct {
	A int
	B string
}

func reflect_call_001_T(__taint_src string) {
	t := T{123, __taint_src}
	s := reflect.ValueOf(t)

	for i := 0; i < s.NumField(); i++ {
		__taint_sink(s.Field(i))
	}
}

func __taint_sink(o interface{}) {
}
