// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
// scene introduction = 路径别名
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_modules_009_T/B/cross_modules_009_T_b
// evaluation information end

import { exportString } from "#root/cross_module_009_T_a.js";

function cross_module_009_T_b() {
  let result = exportString();
  __taint_sink(result);
}

function __taint_sink(o) {}
