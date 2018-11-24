<!--Bài 2 -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bài 2</title>
</head>
<body>
    <?php
    function XuatThongDiep($n) {
        $phan_du = $n % 5;
        switch ($phan_du) {
            case 0:
                echo "<h5>Hello</h5>";
                break;
            case 1:
                echo "<h5>How are you?</h5>";
                break;
            case 2:
                echo "<h5>I’m doing well, thank you</h5>";
                break;
            case 3:
                echo "<h5>See you later</h5>";
                break;
            case 4:
                echo "<h5>Good-bye</h5>";
                break;
            default:
                break;
        }
    }
    XuatThongDiep(5);
    ?>
</body>
</html>
