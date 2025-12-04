
// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 参数值传递->引用传递->map
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/argument_passing_reference_005_F/argument_passing_reference_005_F
// evaluation information end

package main
import "os/exec"

func argument_passing_reference_005_F(__taint_src interface{}) {
	objA := map[string]interface{}{"name": __taint_src}
	objB := map[string]interface{}{"name": "Bob"}

	swap(objA, objB)
	__taint_sink(objA["name"])
}

func swap(obj1, obj2 map[string]interface{}) {
	temp := obj1["name"]
	obj1["name"] = obj2["name"]
	obj2["name"] = temp
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    argument_passing_reference_005_F(__taint_src)
}