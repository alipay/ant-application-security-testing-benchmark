// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
// scene introduction = export_default
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_modules_003_T/A/cross_modules_003_T_a
// evaluation information end

export default function defaultExport(__taint_src) {
  return __taint_src + "_default";
}
