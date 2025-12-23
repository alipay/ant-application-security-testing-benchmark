// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
// scene introduction = 别名
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_008_F/B/cross_module_008_F_b
// evaluation information end
import { execSync } from 'child_process';


import { exportedVariable1 as fooVar } from "../A/cross_module_008_F_a.js";

function cross_module_008_F_b() {
  let result = fooVar;
  __taint_sink(result);
}

function __taint_sink(o) {
  execSync(o);
}

cross_module_008_F_b();
