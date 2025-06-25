# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
# scene introduction = 实例化中动态方法注入
# level = 2+
# bind_url = completeness/single_app_tracing/class/complex_object/inject_data_new_003_T
# evaluation information end

import os
# __new__更复杂的对象创建逻辑
def inject_data_new_003_T(taint_src):
    class Meta(type):
        def __new__(cls, name, bases, attrs):
            # 动态注入一个方法到类中
            def data_transform(self, data):
                return data.upper()  # 将传入的数据转为大写
            attrs['data_transform'] = data_transform

            return super().__new__(cls, name, bases, attrs)


    class ComplexObject(metaclass=Meta):
        def __new__(cls, input_data):
            # 继承 Meta中创建的实例方法
            instance = super().__new__(cls)
            # 在ComplexObject 示例创建过程中 新增一个 tainted_data变量 他的值input_data实际上就是taint_src
            instance.tainted_data = input_data  

            return instance

    # 实例化一个对象
    obj1 = ComplexObject(taint_src)
    # 调用 Meta 中注入的方法，并传入 ComplexObject中的 tainted_data值
    transformed_data = obj1.data_transform(obj1.tainted_data)

    # 污染 sink
    taint_sink(transformed_data)


def taint_sink(o):
    os.system(o)


if __name__ == "__main__":
    taint_src = "taint_src_value"
    inject_data_new_003_T(taint_src)