// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
// scene introduction = 数组/集合->数组对象->push函数
// level = 2
// bind_url = accuracy/object_sensitive/collection/array_object_sensitive_005_T
// evaluation information end
const { execSync } = require('child_process');


function array_object_sensitive_005_T(__taint_src) {
    let s = [];
    s.push(__taint_src);
    let s2 = [];
    s2.push("a");
    __taint_sink(s);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

array_object_sensitive_005_T(taint_src);
