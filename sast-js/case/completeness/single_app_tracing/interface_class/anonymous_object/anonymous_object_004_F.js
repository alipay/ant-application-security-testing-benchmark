// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->接口与类->匿名对象
// scene introduction = 对象字面量
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/anonymous_object/anonymous_object_004_F
// date = 2025-12-17 09:15:43
// evaluation information end
const { execSync } = require("child_process");

function anonymous_object_004_F(__taint_src) {
  // 场景特点：使用对象字面量创建匿名对象但存储非污点数据
  const obj = {
    data: "safe_value",
  };

  __taint_sink(obj.data);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";
anonymous_object_004_F(taint_src);
