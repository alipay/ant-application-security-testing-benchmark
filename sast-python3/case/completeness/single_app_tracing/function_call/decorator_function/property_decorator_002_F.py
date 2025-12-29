# -*- coding: utf-8 -*-
# evaluation information start
# real case = false
# evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->装饰器函数
# scene introduction = property
# level = 2
# bind_url = completeness/single_app_tracing/function_call/decorator_function/property_decorator_002_F
# evaluation information end

import os

class TaintedInput:
    def __init__(self, value):
        self._value = value  

    # 它允许你将类中的某个方法“伪装”成一个属性（attribute），这样你就可以像访问普通属性一样调用它，而不需要加括号 ()
    # 同时 @property 支持 get set del 操作
    @property
    def value(self):
        return self._value

    @value.setter
    def value(self, new_value):
        # setter中通常做一些校验 校验通过才继续修改
        if not isinstance(new_value, str):
            raise ValueError("Value must be a string.")
        self._value = new_value

    @value.deleter
    def value(self):
        del self._value


def property_decorator_002_F(taint_src):
    obj = TaintedInput(taint_src)         
    # 固定语法 del obj.value 是删除值 对应触发 @value.deleter
    del obj.value   

    # 固定语法 obj.value = ... 是修改值，对应触发@value.setter
    obj.value = "_"

    # 调用时直接使用obj.value 而不是obj.value()，不需要加括号
    taint_sink(obj.value)                


def taint_sink(o):
    os.system(o)

if __name__ == '__main__':
    taint_src = "taint_src_value"
    property_decorator_002_F(taint_src)