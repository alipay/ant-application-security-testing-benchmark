// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
// scene introduction = 变量
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_modules_011_T/B/cross_modules_011_T_b
// evaluation information end
import { execSync } from 'child_process';


import { exportedVariable } from "../A/cross_module_011_T_a.js";

function cross_module_011_T_b() {
  let result = exportedVariable;
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

cross_module_011_T_b();
