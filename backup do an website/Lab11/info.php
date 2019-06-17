<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<?php
    session_start();
    if (!isset($_SESSION['username'])) {
        header('location:login.php');
    }
?>
<body>
    <h1>Bạn đã đăng nhập với tên tài khoản '<?php echo $_SESSION['username']; ?>'</h1>
    <a href="logout.php">Đăng xuất</a>
</body>
</html>