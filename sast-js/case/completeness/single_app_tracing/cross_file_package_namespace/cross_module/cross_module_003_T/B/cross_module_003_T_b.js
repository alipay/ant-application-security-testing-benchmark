// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
// scene introduction = export_default
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_modules_003_T/B/cross_module_003_T_b
// evaluation information end
const { execSync } = require('child_process');


import defaultExport from "../A/cross_module_003_T_a.js";

function cross_module_003_T_b() {
  let result = defaultExport();
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";

cross_module_003_T_b(taint_src);
