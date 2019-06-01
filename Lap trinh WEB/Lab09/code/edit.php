<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sửa thông tin xe</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<?php
    require "Database.php";
    $db = new Database;
    
    
    if (isset($_GET['id'])) {
        $id = $_GET['id'];
        $results = $db->query("select * from cars where id = $id");
        $car = mysqli_fetch_array($results);
    }
?>
<body>
    <div class="container">
        <form>
            <input type="hidden"/>
            <div class="form-group">
                <label for="name">Tên xe</label>
                <input type="text" class="form-control" id="name" value="<?php echo $car['name']; ?>" placeholder="Nhập tên xe">
            </div>
            <div class="form-group">
                <label for="year">Năm sản xuất</label>
                <input type="number" class="form-control" id="year" value="<?php echo $car['year']; ?>" placeholder="Nhập năm sản xuất">
            </div>
            <button type="submit" class="btn btn-primary">Lưu</button>
        </form>
    </div>
</body>
</html>