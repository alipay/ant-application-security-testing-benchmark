// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
// scene introduction = module_exports
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_modules_005_T/B/cross_module_005_T_b
// evaluation information end

const { exportString } = require("../A/cross_module_005_T_a.js");

function cross_module_005_T_b() {
  let result = exportString();
  __taint_sink(result);
}

function __taint_sink(o) {}
