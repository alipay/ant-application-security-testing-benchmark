// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感分析->多态->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态
// scene introduction =
// level = 2
// bind_url = accuracy/context_sensitive/polymorphism/polymorphism_002_F
// evaluation information end
function polymorphism_002_F(__taint_src) {
  class Base { }

  class Sub1 extends Base {
    call() {
      return __taint_src;
    }
  }

  class Sub2 extends Base {
    call() {
      return "_";
    }
  }

  const sub = new Sub2();
  __taint_sink(sub.call());
}

function __taint_sink(o) { }
