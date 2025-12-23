// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->装饰器函数
// scene introduction = 类装饰器二阶
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/decorator_function/class_decorator_003_F
// evaluation information end

// 类装饰器（Class Decorators）是一个仍在提案阶段的js特性，并未被正式纳入 ECMAScript 标准。
// 虽然 TypeScript 和 Babel 支持装饰器，但 原生 Node.js/js 不支持。 所以要运行该文件必须安装ts环境或者Babel编译器
const { execSync } = require('child_process');

function class_decorator_003_F(__taint_src) {
  const instance = new DecoratedClass();
  instance.method("_");
}

function classDecorator(target) {
  return class extends target {
    method(input) {
      __taint_sink(input);
      super.method(input);
    }
  };
}

@classDecorator
class DecoratedClass {
  method(input) {}
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

class_decorator_003_F(taint_src);
