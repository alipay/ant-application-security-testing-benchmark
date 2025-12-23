// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->接口与类->匿名对象
// scene introduction = 函数返回
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/anonymous_object/anonymous_object_006_F
// date = 2025-12-17 09:15:43
// evaluation information end
const { execSync } = require("child_process");

function anonymous_object_006_F(__taint_src) {
  // 场景特点：使用函数返回创建匿名对象但存储非污点数据
  function create() {
    return { data: "safe_value" };
  }
  const obj = create();

  __taint_sink(obj.data);
}

function __taint_sink(o) {
  execSync(o);
}

const taint_src = "taint_src_value";
anonymous_object_006_F(taint_src);
