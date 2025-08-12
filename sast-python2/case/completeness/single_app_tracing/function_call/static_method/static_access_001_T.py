# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->静态方法
# scene introduction = 静态变量操作
# level = 2+
# bind_url = completeness/single_app_tracing/function_call/static_method/static_access_001_T
# evaluation information end
import os


def static_access_001_T(taint_src):
    class DataProcessor(object):
      __taint_storage = u"_"  # 静态变量

      @staticmethod
      def set_static_data(src):
        DataProcessor.__taint_storage = src  

      @staticmethod
      def get_static_data():
        return DataProcessor.__taint_storage  

    DataProcessor.set_static_data(taint_src)
    taint_sink(DataProcessor.get_static_data())  


def taint_sink(o):
    os.system(o)

if __name__ == u"__main__":
    taint_src = u"taint_src_value"
    static_access_001_T(taint_src)

