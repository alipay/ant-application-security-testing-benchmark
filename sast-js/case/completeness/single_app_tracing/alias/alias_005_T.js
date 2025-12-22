// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->别名
// scene introduction = 解构参数
// level = 2
// bind_url = completeness/single_app_tracing/alias/alias_005_T
// date = 2025-12-18 06:34:45
// evaluation information end

const { execSync } = require("child_process");

function alias_005_T(__taint_src) {
  const arr = [__taint_src, "save_value"];

  const [a, b] = arr;

  __taint_sink(a);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

alias_005_T(taint_src);
