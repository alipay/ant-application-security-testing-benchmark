# SAST-PHP Benchmark — PHP 污点追踪引擎能力评测靶场

## 概述

| 指标 | 数值 |
|------|------|
| 总 case 数 | 290 个 PHP 文件 |
| 准确度 (accuracy) | 64 |
| 完整度 (completeness) | 226 |
| T (true positive) | 145 |
| F (false positive) | 145 |
| PHP 版本要求 | >= 8.1 |

## 目录结构

```
sast-php/case/
├── accuracy/                          # 准确度 — 引擎区分真/假阳性的能力
│   ├── context_sensitive/             # 上下文敏感
│   │   ├── multi_invoke/              # 多次调用 (4)
│   │   ├── polymorphism/              # 多态 (4)
│   │   └── argument_return_value_passing/  # 参数/返回值传递 (8)
│   ├── path_sensitive/                # 路径敏感
│   │   ├── loop_conditional_stmt/     # 条件循环语句 (6)
│   │   ├── explicit_jump_control/     # 跳转语句 (4)
│   │   └── exception_throw/           # 异常抛出路径 (4)
│   ├── flow_sensitive/                # 流敏感
│   │   ├── normal_stmt/               # 普通语句 (4)
│   │   └── loop_stmt/                 # 循环语句 (4)
│   └── object_field_sensitive/        # 对象/域敏感
│       ├── class_field/               # 类字段 (6)
│       ├── field_len/                 # 域长度 (6)
│       ├── one_collection/            # 一维集合 (6)
│       ├── multidimensional_collection/  # 多维集合 (4)
│       └── object_sensitive/          # 对象敏感 (4)
└── completeness/                      # 完整度 — 引擎跟踪污点传播的完备性
    ├── single_app_tracing/            # 单应用追踪
    │   ├── alias/                     # 别名 + 引用赋值 (6)
    │   ├── control_flow/              # 控制流 (10)
    │   ├── cross_file_package_namespace/  # 跨文件 (56 files, 28 cases)
    │   ├── datatype/                  # 数据类型 + 超全局变量 (20)
    │   ├── exception_error/           # 异常处理 (4)
    │   ├── expression/                # 表达式 (34)
    │   ├── function_call/             # 函数调用 (40)
    │   ├── class/                     # 类与对象 (28)
    │   └── variable_scope/            # 变量作用域 (10)
    └── dynamic_tracing/               # 动态追踪
        ├── eval/                      # eval (4)
        ├── variable_variables/        # 可变变量 (4)
        └── compact_extract/           # compact/extract (4)
```

## 评价体系

### accuracy（准确度）— 64 case

| # | 子维度 | 评价项 | T | F | 说明 |
|---|--------|--------|---|---|------|
| 1 | context_sensitive/multi_invoke | 准确度->上下文敏感->多次调用 | 2 | 2 | 同一函数多次调用，区分污染/安全参数的返回值 |
| 2 | context_sensitive/polymorphism | 准确度->上下文敏感->多态 | 2 | 2 | 抽象类/接口多态，子类实现传递或净化污点 |
| 3 | context_sensitive/argument_return_value_passing | 准确度->上下文敏感->参数/返回值传递 | 4 | 4 | 参数值传递、返回值传递、多层参数/返回值链 |
| 4 | path_sensitive/loop_conditional_stmt | 准确度->路径敏感->条件循环语句 | 3 | 3 | if/while/if-else 分支中的污点路径区分 |
| 5 | path_sensitive/explicit_jump_control | 准确度->路径敏感->跳转语句 | 2 | 2 | break/return/continue 对污点传播路径的影响 |
| 6 | path_sensitive/exception_throw | 准确度->路径敏感->异常抛出 | 2 | 2 | try/catch 块中异常前后的污点传播 |
| 7 | flow_sensitive/normal_stmt | 准确度->流敏感->普通语句 | 2 | 2 | 赋值顺序、变量覆盖对污点的影响 |
| 8 | flow_sensitive/loop_stmt | 准确度->流敏感->循环语句 | 2 | 2 | for/while/foreach 循环体内语句执行顺序 |
| 9 | object_field_sensitive/class_field | 准确度->对象域敏感->类字段 | 3 | 3 | 类字段赋值、getter/setter、嵌套类字段 |
| 10 | object_field_sensitive/field_len | 准确度->对象域敏感->域长度 | 3 | 3 | 2~4 层嵌套对象字段访问路径 |
| 11 | object_field_sensitive/one_collection | 准确度->对象域敏感->一维集合 | 3 | 3 | 数组索引、关联数组 key、array_push |
| 12 | object_field_sensitive/multidimensional_collection | 准确度->对象域敏感->多维集合 | 2 | 2 | 二维数组、嵌套关联数组 |
| 13 | object_field_sensitive/object_sensitive | 准确度->对象域敏感->对象敏感 | 2 | 2 | 同类不同对象实例区分 |

### completeness/single_app_tracing（完整度-单应用追踪）— 214 case

| # | 子维度 | 评价项 | T | F | 说明 | PHP 特有 |
|---|--------|--------|---|---|------|----------|
| 14 | alias | 完整度->单应用追踪->别名 | 3 | 3 | 普通变量赋值、引用赋值 `&$var`、unset 引用 | `&$var` |
| 15 | control_flow/conditional_stmt | 完整度->单应用追踪->控制流 | 3 | 3 | if/switch/match 分支 | `match` (PHP 8) |
| 16 | control_flow/loop_stmt | 完整度->单应用追踪->控制流 | 2 | 2 | for/foreach/while 循环 | — |
| 17 | cross_file: include/require | 完整度->单应用追踪->跨文件 | 2 | 2 | include/require 引入外部函数/类 | — |
| 18 | cross_file: require_once | 完整度->单应用追踪->跨文件 | 2 | 2 | require_once 引入函数/类 | — |
| 19 | cross_file: namespace/use | 完整度->单应用追踪->跨文件 | 2 | 2 | 命名空间 + use/use as | `namespace` |
| 20 | cross_file: autoload | 完整度->单应用追踪->跨文件 | 2 | 2 | spl_autoload_register 自动加载 | `spl_autoload_register` |
| 21 | cross_file: trait (跨文件) | 完整度->单应用追踪->跨文件 | 2 | 2 | 独立文件定义 trait，use 后调用 | `trait` |
| 22 | cross_file: interface (跨文件) | 完整度->单应用追踪->跨文件 | 2 | 2 | 独立文件定义接口，实现类调用 | — |
| 23 | cross_file: inheritance (跨文件) | 完整度->单应用追踪->跨文件 | 2 | 2 | 独立文件定义父类/抽象类，子类继承 | — |
| 24 | datatype: string | 完整度->单应用追踪->数据类型 | 2 | 2 | 字符串拼接、substr 截取、常量覆盖、str_replace | — |
| 25 | datatype: array | 完整度->单应用追踪->数据类型 | 2 | 2 | 数组元素存取、array_push/pop、安全 key | — |
| 26 | datatype: int | 完整度->单应用追踪->数据类型 | 1 | 1 | 整数转字符串拼接、纯整数运算 | — |
| 27 | datatype: superglobal | 完整度->单应用追踪->数据类型 | 5 | 5 | $_POST/$_COOKIE/$_REQUEST/$_SERVER/$_SESSION | 超全局变量 |
| 28 | exception_error | 完整度->单应用追踪->异常处理 | 2 | 2 | try/catch/finally 块中污点传播 | — |
| 29 | expression: basic | 完整度->单应用追踪->表达式 | 2 | 2 | 赋值、拼接 | — |
| 30 | expression: conditional | 完整度->单应用追踪->表达式 | 2 | 2 | 三元运算符、null 合并 `??` | `??` |
| 31 | expression: string_interpolation | 完整度->单应用追踪->表达式 | 2 | 2 | 双引号插值、花括号语法、单引号不插值 | `"$var"` `"{$var}"` |
| 32 | expression: type_cast | 完整度->单应用追踪->表达式 | 1 | 1 | `(string)` 保留污点、`(int)` 语义丢失 | — |
| 33 | expression: destructuring | 完整度->单应用追踪->表达式 | 2 | 2 | `list()` / `[$a,$b]` 数组解构 | `list()` |
| 34 | expression: spread | 完整度->单应用追踪->表达式 | 2 | 2 | `...$args` 函数参数展开、数组解包 | `...` |
| 35 | expression: nullsafe | 完整度->单应用追踪->表达式 | 2 | 2 | `?->` 运算符、链式 nullsafe | `?->` (PHP 8) |
| 36 | expression: heredoc/nowdoc | 完整度->单应用追踪->表达式 | 2 | 2 | heredoc 插值传递污点、nowdoc 不插值 | `<<<EOT` |
| 37 | expression: string_func | 完整度->单应用追踪->表达式 | 2 | 2 | sprintf/implode/str_replace | — |
| 38 | function_call: anonymous/closure | 完整度->单应用追踪->函数调用 | 2 | 2 | 匿名函数闭包、箭头函数 `fn()` | `fn()` (PHP 7.4) |
| 39 | function_call: argument_passing | 完整度->单应用追踪->函数调用 | 3 | 3 | 值传递、引用传参 `&$param`、多层调用 | `&$param` |
| 40 | function_call: return_value | 完整度->单应用追踪->函数调用 | 2 | 2 | 返回值传递、嵌套函数返回值链 | — |
| 41 | function_call: chained_call | 完整度->单应用追踪->函数调用 | 2 | 2 | `$obj->a()->b()` 链式方法调用 | — |
| 42 | function_call: higher_order | 完整度->单应用追踪->函数调用 | 2 | 2 | array_map/array_filter/usort | — |
| 43 | function_call: static_method | 完整度->单应用追踪->函数调用 | 2 | 2 | `ClassName::method()` 静态方法 | — |
| 44 | function_call: variable_function | 完整度->单应用追踪->函数调用 | 2 | 2 | `$func()` / `call_user_func` | `$func()` |
| 45 | function_call: named_arg | 完整度->单应用追踪->函数调用 | 2 | 2 | `func(name: $val)` 命名参数 | PHP 8 |
| 46 | function_call: first_class_callable | 完整度->单应用追踪->函数调用 | 2 | 2 | `strlen(...)` 语法获取函数引用 | PHP 8.1 |
| 47 | function_call: generator | 完整度->单应用追踪->函数调用 | 2 | 2 | `yield` / `yield from` 生成器 | — |
| 48 | class: simple_object | 完整度->单应用追踪->类与对象 | 2 | 2 | 对象属性存取 | — |
| 49 | class: subclass | 完整度->单应用追踪->类与对象 | 2 | 2 | 子类继承/重写 | — |
| 50 | class: interface_impl | 完整度->单应用追踪->类与对象 | 2 | 2 | 接口实现类 | — |
| 51 | class: trait | 完整度->单应用追踪->类与对象 | 2 | 2 | trait 方法/属性传递 | `trait` |
| 52 | class: magic_method | 完整度->单应用追踪->类与对象 | 3 | 3 | `__get`/`__set`/`__toString`/`__invoke`/`__call` | 魔术方法 |
| 53 | class: abstract_class | 完整度->单应用追踪->类与对象 | 1 | 1 | 抽象类子类实现 | — |
| 54 | class: anonymous_class | 完整度->单应用追踪->类与对象 | 1 | 1 | `new class {}` 匿名类 | — |
| 55 | class: late_static_binding | 完整度->单应用追踪->类与对象 | 1 | 1 | `static::method()` 后期静态绑定 | `static::` |
| 56 | class: enum | 完整度->单应用追踪->类与对象 | 1 | 1 | enum 方法传递污点 | PHP 8.1 |
| 57 | class: readonly | 完整度->单应用追踪->类与对象 | 1 | 1 | readonly 属性 | PHP 8.2 |
| 58 | variable_scope: global | 完整度->单应用追踪->变量作用域 | 2 | 2 | `global $var` / `$GLOBALS` | `global` |
| 59 | variable_scope: static | 完整度->单应用追踪->变量作用域 | 1 | 1 | 静态变量跨调用保留 | `static $var` |
| 60 | variable_scope: closure_use | 完整度->单应用追踪->变量作用域 | 2 | 2 | `use ($var)` / `use (&$var)` | `use` |

### completeness/dynamic_tracing（完整度-动态追踪）— 12 case

| # | 子维度 | 评价项 | T | F | 说明 | PHP 特有 |
|---|--------|--------|---|---|------|----------|
| 61 | eval | 完整度->动态追踪->eval | 2 | 2 | eval 拼接执行、eval 赋值后传 sink | `eval()` |
| 62 | variable_variables | 完整度->动态追踪->可变变量 | 2 | 2 | `$$name` 间接引用、循环中可变变量 | `$$var` |
| 63 | compact_extract | 完整度->动态追踪->compact与extract | 2 | 2 | `compact()` 打包 / `extract()` 解包 | `compact`/`extract` |

## PHP 特有语法覆盖汇总

| 语法特性 | 所在维度 | case 数 |
|----------|----------|---------|
| 引用赋值 `&$var` | alias, argument_passing | 8 |
| 魔术方法 `__get/__set/__toString/__invoke/__call` | class/magic_method | 6 |
| trait | class/trait, cross_file_trait | 8 |
| 闭包 `use ($var)` / `use (&$var)` | variable_scope/closure_use | 4 |
| 箭头函数 `fn()` | function_call/anonymous_function | 2 |
| match 表达式 | control_flow/conditional_stmt | 2 |
| 可变函数 `$func()` / `call_user_func` | function_call/variable_function | 4 |
| null 合并 `??` | expression/conditional_expression | 2 |
| nullsafe `?->` | expression/nullsafe | 4 |
| 字符串插值 `"$var"` / `"{$var}"` | expression/string_interpolation | 4 |
| heredoc / nowdoc | expression/heredoc | 4 |
| 数组解构 `list()` / `[$a,$b]` | expression/destructuring | 4 |
| spread `...$args` | expression/spread | 4 |
| named arguments | function_call/named_arg | 4 |
| first-class callable `fn(...)` | function_call/first_class_callable | 4 |
| generator / yield / yield from | function_call/generator | 4 |
| enum | class/enum | 2 |
| readonly | class/readonly | 2 |
| late static binding `static::` | class/late_static_binding | 2 |
| 匿名类 `new class {}` | class/anonymous_class | 2 |
| 超全局变量 `$_POST/$_COOKIE/$_REQUEST/$_SERVER/$_SESSION` | datatype/superglobal | 10 |
| eval | dynamic_tracing/eval | 4 |
| 可变变量 `$$var` | dynamic_tracing/variable_variables | 4 |
| compact / extract | dynamic_tracing/compact_extract | 4 |
| `global $var` / `$GLOBALS` | variable_scope/global_variable | 4 |
| 静态变量 `static $var` | variable_scope/static_variable | 2 |
| namespace / use / use as | cross_file/namespace_use | 4 |
| include / require / require_once | cross_file/include_require, require_once | 8 |
| spl_autoload_register | cross_file/autoload | 4 |

## case 文件格式

```php
<?php
// evaluation information start
// real case = true|false
// evaluation item = 准确度|完整度->...->...
// scene introduction = 场景描述
// level = 1|2|3
// bind_url = accuracy|completeness/.../case_name
// evaluation information end

function case_name_001_T($__taint_src) {
    // 污点传播逻辑
    __taint_sink($result);
}

function __taint_sink($o) {
    shell_exec($o);
}

$taint_src = "taint_src_value";
case_name_001_T($taint_src);
```

| 字段 | 说明 |
|------|------|
| real case | `true` = T 用例（存在真实污点链路），`false` = F 用例（不存在） |
| evaluation item | 中文评价维度路径 |
| scene introduction | 场景简述 |
| level | 难度等级：1=基础, 2=中等, 3=高级 |
| bind_url | 从 accuracy/completeness 开始的相对路径（不含 .php） |
| sink | 统一 `shell_exec()` 命令注入类 |
| source | 函数参数 `$__taint_src` 或超全局变量 `$_GET/$_POST` 等 |
