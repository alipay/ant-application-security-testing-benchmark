<?php
// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感->多态
// scene introduction = 接口多态，实现类返回安全数据
// level = 2
// bind_url = accuracy/context_sensitive/polymorphism/polymorphism_004_F
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

function polymorphism_004_F($__taint_src) {
    $processor = new SanitizeProcessor();
    $result = $processor->process($__taint_src);
    __taint_sink($result);
}

$taint_src = "taint_src_value";
polymorphism_004_F($taint_src);
