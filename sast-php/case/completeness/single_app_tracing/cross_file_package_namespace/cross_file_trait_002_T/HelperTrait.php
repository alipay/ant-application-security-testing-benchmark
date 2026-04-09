<?php

trait HelperTrait {
    private $data;

    public function setData($input) {
        $this->data = $input;
    }

    public function getData() {
        return $this->data;
    }
}
