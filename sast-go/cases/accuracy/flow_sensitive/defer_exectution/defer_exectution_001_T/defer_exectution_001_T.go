
// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感分析->延迟执行
// scene introduction = defer
// level = 3
// bind_url = accuracy/flow_sensitive/defer_exectution/defer_exectution_001_T/defer_exectution_001_T
// evaluation information end

package defer_exectution_001_T

func defer_exectution_001_T(__taint_src string) {
	result := __taint_src
	defer func() {
		result = "aa"
	}()
	___taint_sink(result)
}

func ___taint_sink(o interface{}) {
}
