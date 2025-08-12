
// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同结构体
// scene introduction = 结构体对象->5层对象
// level = 2
// bind_url = accuracy/object_sensitive/struct/struct_deep5_003_T/struct_deep5_003_T
// evaluation information end

package main
import "os/exec"

type DeepB01 struct {
	deepB02 DeepB02
}

type DeepB02 struct {
	deepB03 DeepB03
}

type DeepB03 struct {
	deepB04 DeepB04
}

type DeepB04 struct {
	deepB05 DeepB05
}

type DeepB05 struct {
	deepB06 string
}

func struct_deep5_003_T(__taint_src string) {
	//创建第一个 DeepC01 实例，并为其嵌套字段赋值
	obj1 := DeepB01{}
	//创建第二个 DeepC01 实例
	//obj2 := DeepC01{}
	deepB05 := DeepB05{
		deepB06: __taint_src,
	}
	obj1.deepB02.deepB03.deepB04.deepB05 = deepB05

	__taint_sink(obj1)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
//模拟污点数据的汇聚点
}

func main() {
    __taint_src := "taint_src_value"
    struct_deep5_003_T(__taint_src)
}