<!--Bài 3 -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bài 3</title>
</head>
<body>
    <h1>Dùng vòng lặp for</h1>
    <?php
        //Dùng for
        for ($i=1;$i<=100;$i+=2) {
            echo "$i &emsp;";
        }
    ?>
    <h1>Dùng vòng lặp while</h1>
    <?php
        //Dùng while
        $i = 1;
        while ($i<=100) {
            echo "$i &emsp;";
            $i+=2;
        }
    ?>
</body>
</html>
