<!--Bài 1 -->
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Bài 1</title>
    </head>
    <body>
        <?php
            $x = 10;
            $y = 7;
            $kq_cong = $x + $y;
            $kq_tru = $x - $y;
            $kq_nhan = $x * $y;
            if ($y != 0) {
                $kq_chia = $x / $y;
                $kq_du = $x % $y;
            } else {
                $kq_chia = "Lỗi chia cho 0";
                $kq_du = "Lỗi chia cho 0";
            }
            echo "$x + $y = $kq_cong <br/>";
            echo "$x - $y = $kq_tru <br/>";
            echo "$x * $y = $kq_nhan <br/>";
            echo "$x / $y = $kq_chia <br/>";
            echo "$x % $y = $kq_du <br/>";
        ?>
    </body>
</html>
