// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->别名
// scene introduction = 解构参数
// level = 2
// bind_url = completeness/single_app_tracing/alias/alias_006_F
// date = 2025-12-18 06:34:45
// evaluation information end

const { execSync } = require("child_process");

function alias_006_F(__taint_src) {
  const obj = { a: __taint_src, b: "save_value" };

  const { a, b: result } = obj;

  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

alias_006_F(taint_src);
