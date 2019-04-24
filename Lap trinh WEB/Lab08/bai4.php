<!--Bài 4 -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bài 4</title>
</head>
<body>
    <table border="1" style="border:solid 1px black; border-spacing: 0; background: yellow;">
        <?php for ($i=1;$i<=7;$i++) { ?>
            <tr>
                <?php for ($j=1;$j<=7;$j++) { ?>
                    <td style="width: 25px; padding: 10px; text-align:center; font-size: 23px;"><?php echo $i*$j ?></td>
                <?php } ?>
            </tr>
        <?php } ?>
    </table>
</body>
</html>
