<?php
// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感->多态
// scene introduction = 接口多态，实现类传递污点数据
// level = 3
// bind_url = accuracy/context_sensitive/polymorphism/polymorphism_003_T
// evaluation information end

function __taint_sink($o) {
    shell_exec($o);
}

interface DataProcessor {
    public function process($data);
}

class PassthroughProcessor implements DataProcessor {
    public function process($data) {
        return $data;
    }
}

class SanitizeProcessor implements DataProcessor {
    public function process($data) {
        return "sanitized";
    }
}

function polymorphism_003_T($__taint_src) {
    $processor = new PassthroughProcessor();
    $result = $processor->process($__taint_src);
    __taint_sink($result);
}

$taint_src = "taint_src_value";
polymorphism_003_T($taint_src);
