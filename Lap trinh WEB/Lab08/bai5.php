<!--Bài 5 -->
<!DOCTYPE html>
<?php
    $soa = "";
    $sob = "";
    $pheptoan = 1;
    $ketqua = "";
    if (isset($_GET['soa']) && isset($_GET['sob']) && isset($_GET['pheptoan'])) {
        $soa = $_GET['soa'];
        $sob = $_GET['sob'];
        $pheptoan = $_GET['pheptoan'];
        if (is_numeric($soa) && is_numeric($sob)) {
            switch ($pheptoan) {
                case 1:
                    $kq = $soa + $sob;
                    $ketqua = "$soa + $sob = $kq";
                    break;
                case 2:
                    $kq = $soa - $sob;
                    $ketqua = "$soa - $sob = $kq";
                    break;
                case 3:
                    $kq = $soa * $sob;
                    $ketqua = "$soa * $sob = $kq";
                    break;
                case 4:
                    if ($sob != 0) {
                        $kq = $soa / $sob;
                        $ketqua = "$soa / $sob = $kq";
                    } else {
                        $ketqua = "$soa / $sob = lỗi chia cho 0";
                    }
                    break;
                case 5:
                    $kq = pow($soa,$sob);
                    $ketqua = "$soa ^ $sob = $kq";
                    break;
                case 6:
                    if ($soa != 0) {
                        $kq = 1 / $soa;
                        $ketqua = "1 / $soa = $kq";
                    } else {
                        $ketqua = "1 / $soa = lỗi chia cho 0";
                    }
                    break;
                default:
                    break;
            }
        } else {
            $ketqua = "số nhập vào không hợp lệ";
        }

    }
?>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bài 5</title>
</head>
<body>
    <form action="" method="get">
        <table border="1">
            <tr>
                <td><input name="soa" type="text" placeholder="Nhập số a" value="<?php echo $soa; ?>" /></td>
            <tr/>
            <tr>
                <td><input name="sob" type="text" placeholder="Nhập số b" value="<?php echo $sob; ?>" /></td>
            <tr/>
            <tr>
                <td>
                    <input type="radio" name="pheptoan" value="1" <?php echo $pheptoan == 1 ? 'checked' : '' ?>/>Cộng
                    <input type="radio" name="pheptoan" value="2" <?php echo $pheptoan == 2 ? 'checked' : '' ?>/>Trừ
                    <input type="radio" name="pheptoan" value="3" <?php echo $pheptoan == 3 ? 'checked' : '' ?>/>Nhân
                    <input type="radio" name="pheptoan" value="4" <?php echo $pheptoan == 4 ? 'checked' : '' ?>/>Chia
                    <input type="radio" name="pheptoan" value="5" <?php echo $pheptoan == 5 ? 'checked' : '' ?>/>Lũy thừa
                    <input type="radio" name="pheptoan" value="6" <?php echo $pheptoan == 6 ? 'checked' : '' ?>/>Nghịch đảo
                </td>
            <tr/>
            <tr>
                <td>
                    <input type="submit" value="Tính"/>
                    <!-- <input type="button" value="Làm mới" onclick="window.location.href += '';"/> -->
                    <input type="button" name="" value="Làm mới" onclick="window.location.href = window.location.href.split(/[?#]/)[0];"/>
                </td>
            </tr>
            <tr>
                <td>
                    Kết quả phép tính: <?php echo (isset($ketqua)) ? $ketqua : "0"; ?>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
