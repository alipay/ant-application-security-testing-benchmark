# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 准确度->路径敏感分析->异常抛出和捕获
# scene introduction = 异常抛出-多个except分支
# level = 3
# bind_url = accuracy/path_sensitive/exception_throw/exception_multiple_except_001_T
# evaluation information end
import os

def exception_multiple_except_001_T(taint_src):
    try:
        # 抛出ValueError异常
        raise ValueError(taint_src)
    except ValueError as e:
        # 匹配到ValueError，执行这个分支
        taint_sink(taint_src)  # 应该检出 - 同一分支传递污点数据
    except TypeError as e:
        # 不会执行到这个分支
        pass
    except Exception as e:
        # 不会执行到这个分支（ValueError已匹配）
        pass

def taint_sink(o):
    os.system(o)

# 示例调用
if __name__ == "__main__":
    taint_src = "taint_src_value"
    exception_multiple_except_001_T(taint_src)