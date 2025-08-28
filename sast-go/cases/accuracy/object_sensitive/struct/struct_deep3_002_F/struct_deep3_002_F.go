
// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同结构体
// scene introduction = 结构体对象->3层对象
// level = 2
// bind_url = accuracy/object_sensitive/struct/struct_deep3_002_F/struct_deep3_002_F
// evaluation information end

package main
import (
	"os/exec"
	"fmt"
)

type DeepC01 struct {
	deepC02 DeepC02
}

type DeepC02 struct {
	deepC03 DeepC03
}

type DeepC03 struct {
	deepC04 string
}

func struct_deep3_002_F(__taint_src string) {
	//创建第一个 DeepC01 实例，并为其嵌套字段赋值
	obj1 := DeepC01{}
	//创建第二个 DeepC01 实例
	obj2 := DeepC01{}
	obj1.deepC02.deepC03.deepC04 = __taint_src
	__taint_sink(obj2)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
//模拟污点数据的汇聚点
}

func main() {
    __taint_src := "taint_src_value"
    struct_deep3_002_F(__taint_src)
}