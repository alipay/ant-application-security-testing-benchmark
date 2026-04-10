<?php

trait HelperTrait {
    private $data;

    public function setData($input) {
        $this->data = "safe_value";
    }

    public function getData() {
        return $this->data;
    }
}
