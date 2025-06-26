# -*- coding: utf-8 -*-
# evaluation information start
# real case = true
# evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
# scene introduction = del运算符->对象field
# level = 2+
# bind_url = completeness/single_app_tracing/expression/special_expression/del_expression_001_T
# evaluation information end
import os


def del_expression_001_T(taint_src):
    class EmployeeClass:
        def __init__(self, firstname, lastname):
            self.firstname = firstname
            self.lastname = lastname

    Employee = EmployeeClass(firstname='Bob', lastname=taint_src)

    del Employee.firstname
    taint_sink(Employee.lastname)


def taint_sink(o):
    os.system(o)


if __name__ == '__main__':
    taint_src = "taint_src_value"
    del_expression_001_T(taint_src)

