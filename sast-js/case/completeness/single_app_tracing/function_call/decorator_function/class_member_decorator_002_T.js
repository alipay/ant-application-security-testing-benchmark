// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->装饰器函数
// scene introduction = member装饰器
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/decorator_function/class_member_decorator_002_T
// evaluation information end

// 类装饰器（Class Decorators）是一个仍在提案阶段的js特性，并未被正式纳入 ECMAScript 标准。
// 虽然 TypeScript 和 Babel 支持装饰器，但 原生 Node.js/js 不支持。 所以要运行该文件必须安装ts环境或者Babel编译器
const { execSync } = require('child_process');

function class_member_decorator_002_T(__taint_src) {
  
  function memberDecorator(originalMethod, context) {
   return function (...args) {
      const taintData = args[0];
      __taint_sink(taintData);
      return originalMethod.apply(this, args);
    };
  }

  class AnotherClass {
    @memberDecorator
    someMethod(input) { }
  }

  const instance = new AnotherClass();
  instance.someMethod(__taint_src);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

class_member_decorator_002_T(taint_src);
