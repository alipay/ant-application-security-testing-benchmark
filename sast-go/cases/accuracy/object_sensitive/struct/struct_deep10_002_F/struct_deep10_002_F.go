
// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同结构体
// scene introduction = 结构体对象->10层对象
// level = 2
// bind_url = accuracy/object_sensitive/struct/struct_deep10_002_F/struct_deep10_002_F
// evaluation information end

package main
import (
	"os/exec"
	"fmt"
)

type DeepA01 struct {
	deepA02 DeepA02
}

type DeepA02 struct {
	deepA03 DeepA03
}

type DeepA03 struct {
	deepA04 DeepA04
}

type DeepA04 struct {
	deepA05 DeepA05
}

type DeepA05 struct {
	deepA06 DeepA06
}

type DeepA06 struct {
	deepA07 DeepA07
}

type DeepA07 struct {
	deepA08 DeepA08
}

type DeepA08 struct {
	deepA09 DeepA09
}
type DeepA09 struct {
	deepA10 DeepA10
}
type DeepA10 struct {
	deepA11 string
}

func struct_deep10_002_F(__taint_src string) {
	//创建第一个 DeepC01 实例，并为其嵌套字段赋值
	obj1 := DeepA01{}
	//创建第二个 DeepC01 实例
	obj2 := DeepA01{}
	obj1.deepA02.deepA03.deepA04.deepA05.deepA06.deepA07.deepA08.deepA09.deepA10.deepA11 = __taint_src

	__taint_sink(obj2)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
//模拟污点数据的汇聚点
}

func main() {
    __taint_src := "taint_src_value"
    struct_deep10_002_F(__taint_src)
}