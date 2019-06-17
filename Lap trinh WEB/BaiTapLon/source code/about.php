<!doctype html>
<html lang="en">
<head>
    <?php include('widget/_head.php'); ?>
</head>
<body>
<!-- nhúng phần header -->
<?php include('widget/_header.php') ?>

<!-- điều hướng trang -->
<div class="inner-header">
    <div class="container">
        <div class="pull-left">
            <h6 class="inner-title">Giới thiệu</h6>
        </div>
        <div class="pull-right">
            <div class="beta-breadcrumb font-large">
                <a href="index.php">Trang chủ</a> / <span>Giới thiệu</span>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>

<!-- nội dung trang -->
<div class="container">
    <div id="content">
        <?php echo $companyInfo['about_content']; ?>
    </div> <!-- #content -->
</div> <!-- .container -->

<!-- nhúng phần footer -->
<?php include('widget/_footer.php') ?>

<!-- script dùng chung -->
<?php include('widget/_script.php') ?>

</body>
</html>
