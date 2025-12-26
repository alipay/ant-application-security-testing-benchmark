# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->流程控制语句->条件语句
# scene introduction = matchClass
# level = 2
# bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/conditional_matchClass_002_F
# evaluation information end


import os


def conditional_matchClass_002_F(taint_src):
    class MyClass:
        __match_args__ = ('x', 'y')  # 定义匹配参数顺序

        def __init__(self, x, y):
            self.x = x
            self.y = y

    obj = MyClass(taint_src, "safe_value")
    match obj:
        case MyClass(x, y):  # 使用 MatchClass 匹配类实例
            taint_sink(y)


def taint_sink(o):
    os.system(str(o))


if __name__ == "__main__":
    taint_src = "taint_src_value"
    conditional_matchClass_002_F(taint_src)
