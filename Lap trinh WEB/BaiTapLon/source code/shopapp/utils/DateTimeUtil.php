<?php
/**
 * Created by PhpStorm.
 * User: THANHLONG-PC
 * Date: 04/06/2019
 * Time: 7:21 PM
 */

class DateTimeUtil
{
    public static function getCurrentDateTime_YMD_HMS() {
        date_default_timezone_set("Asia/Ho_Chi_Minh");
        return date('Y-m-d h:m:s');
    }
}
