<?php

class PasswordEncodeUtil {
    public static function getPasswordEncode($password) {
        return md5($password);
    }
}
