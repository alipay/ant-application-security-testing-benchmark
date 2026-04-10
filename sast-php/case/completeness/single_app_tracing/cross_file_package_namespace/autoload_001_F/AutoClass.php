<?php

class AutoClass {
    private $data;

    public function __construct($input) {
        $this->data = "safe_value";
    }

    public function getData() {
        return $this->data;
    }
}
