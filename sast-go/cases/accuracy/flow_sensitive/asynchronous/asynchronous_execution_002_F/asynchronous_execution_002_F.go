
// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感分析->异步执行
// scene introduction = 并发-Goroutine，Channel
// level = 4
// bind_url = accuracy/flow_sensitive/asynchronous/asynchronous_execution_002_F/asynchronous_execution_002_F
// evaluation information end

package main
import "os/exec"

import (
	"sync"
)

func asynchronous_execution_002_F(__taint_src string) {
	var wg sync.WaitGroup
	wg.Add(1)
	ch := make(chan string)
	go worker(ch, &wg)
	ch <- "_"
	wg.Wait()
}

func worker(ch chan string, wg *sync.WaitGroup) {
	defer wg.Done()
	__taint_sink(<-ch)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    asynchronous_execution_002_F(__taint_src)
}