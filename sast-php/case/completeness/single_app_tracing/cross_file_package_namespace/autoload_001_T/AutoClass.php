<?php

class AutoClass {
    private $data;

    public function __construct($input) {
        $this->data = $input;
    }

    public function getData() {
        return $this->data;
    }
}
