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
    if (isset($_SESSION['username'])) {
        header('location:info.php');
    } else {
        if (isset($_POST['username']) && isset($_POST['password'])) {
            $username = $_POST['username'];
            $password = $_POST['password'];
            if ($username == 'admin' && $password == 'admin') {
                $_SESSION['username'] = $username;
                header('location:info.php');
            } else {
                $_SESSION['message'] = 'Tên tài khoản hoặc mật khẩu không đúng';
            }
        } else {
            unset($_SESSION['message']);
        }
    }
?>
<body>

    <h1>Đăng nhập</h1>
    <form action="login.php" method="post">
        Tên tài khoản:<input type="text" name="username"></br></br>
        Mật khẩu:<input type="text" name="password"></br></br>
        <button type="submit">Đăng nhập</button>
    </form>
    <?php echo isset($_SESSION['message']) ? "<p>$_SESSION[message]</p>" : "" ;?>
</body>
</html>
