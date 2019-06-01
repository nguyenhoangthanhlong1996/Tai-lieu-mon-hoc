<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Danh sách xe</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    
</head>
<?php
    require "Database.php";
    $db = new Database;
    $cars = $db->query("select * from cars");

    if (isset($_GET['id'])) {
        $id = $_GET['id'];
        $results = $db->query("select * from cars where id = $id");
        $car = mysqli_fetch_array($results);
    }
    session_start();
?>
<body>
    <div class="container">
        <h3 id="message"><?php echo isset($_SESSION["message"]) ? $_SESSION["message"] : '' ?></h3>
        <h1><?php echo isset($id) ? 'Sửa thông tin xe' : 'Thêm thông tin xe'; ?></h1>
        <form action="save.php" method="GET">
            <input type="hidden" name="id" value="<?php echo isset($id) ? $id : '' ?>">
            <div class="form-group">
                <label for="name">Tên xe</label>
                <input type="text" name="name" class="form-control" id="name" value="<?php echo isset($car['name']) ? $car['name'] : ''; ?>" placeholder="Nhập tên xe">
            </div>
            <div class="form-group">
                <label for="year">Năm sản xuất</label>
                <input type="number" name="year" class="form-control" id="year" value="<?php echo isset($car['year']) ? $car['year'] : ''; ?>" placeholder="Nhập năm sản xuất">
            </div>
            <button type="submit" class="btn btn-primary"><?php echo isset($id) ? 'Cập nhật' : 'Thêm'; ?></button>
        </form>
        <a href="index.php">Quay về trang chủ</a>
        <h1>Danh sách các xe</h1>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Year</th>
                    <th scope="col">Chức năng</th>
                </tr>
            </thead>
            <tbody>
                <?php while ($row = mysqli_fetch_array($cars))
                    echo "<tr>
                            <th>$row[id]</th>
                            <td>$row[name]</td>
                            <td>$row[year]</td>
                            <td>
                                <a href='index.php?id=$row[id]'>Sửa</a>
                                <a href='delete.php?id=$row[id]' style='margin-left: 10px;'>Xóa</a>
                            </td>
                        </tr>";
                ?>
            </tbody>
        </table>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>
        $( document ).ready(function() {
            $("#message").slideUp(300);
        });
    </script>
</body>
<?php session_destroy(); ?>
</html>