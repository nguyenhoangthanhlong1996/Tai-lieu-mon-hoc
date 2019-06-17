<?php

if (isset($_GET['categoryId'])) {
    $categoryId = $_GET['categoryId'];
    require 'database/ProductDAO.php';
    $productDAO = new ProductDAO();
    // List sản phẩm theo danh mục
    $listProducts = $productDAO->getListProductByCategoryId($categoryId);
    if ($listProducts == null) {
        header('Location: 404.php');
    }
} else {
    header('Location: 404.php');
}
?>
<!doctype html>
<html lang="en">
<head>
    <?php include('widget/_head.php'); ?>
</head>
<body>
<!-- nhúng phần header -->
<?php include('widget/_header.php') ?>

<!-- dieu huong trang -->
<div class="inner-header">
    <div class="container">
        <div class="pull-left">
            <h6 class="inner-title">Sản phẩm</h6>
        </div>
        <div class="pull-right">
            <div class="beta-breadcrumb font-large">
                <a href="index.php">Trang chủ</a> / <span>Sản phẩm</span>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>

<!-- noi dung trang -->
<div class="container">
    <div id="content" class="space-top-none">
        <div class="main-content">
            <div class="space60">&nbsp;</div>
            <div class="row">
                <div class="col-sm-3">
                    <ul class="aside-menu">
                        <?php
                        foreach ($categories as $category) {
                            echo "<li><a href='category.php?categoryId=$category[id]'>$category[name]</a></li>";
                        }
                        ?>
                    </ul>
                </div>
                <div class="col-sm-9">
                    <div class="beta-products-list">
                        <?php
                        // Tên danh mục
                        $categoryName = $categoryDAO->getCategoryById($categoryId)['name'];
                        ?>
                        <h4><?php echo $categoryName; ?></h4>
                        <div class="row">
                            <?php
                            foreach ($listProducts as $product) {
                                $id =  $product['id'];
                                $name = $product['name'];
                                $price = number_format($product['price'], 0, '', ',');
                                $image = $product['cover_image'];
                                echo "
                                <div class='col-sm-3' style='margin-top: 50px;'>
                                    <div class='single-item'>
                                        <div class='single-item-header'><a href='product_detail.php?productId=$id'><img src='$product[cover_image]' style='width: 270px; height: 270px;' alt=''></a></div>
                                        <div class='single-item-body'>
                                            <p class='single-item-title' style='height: 50px;'>$name</p>
                                            <p class='single-item-price'><span>$price</span></p>
                                        </div>
                                        <div class='single-item-caption' style='margin-top: 10px;'>
                                            <a class='add-to-cart pull-left'><i class='fa fa-shopping-cart'></i></a>
                                            <a class='beta-btn primary' href='product_detail.php?productId=$id'>Chi tiết <i class='fa fa-chevron-right'></i></a>
                                            <div class='clearfix'></div>
                                        </div>
                                    </div>
                                </div>";
                            }
                            ?>
                        </div>
                    </div> <!-- .beta-products-list -->
                </div>
            </div> <!-- end section with sidebar and main content -->


        </div> <!-- .main-content -->
    </div> <!-- #content -->
</div> <!-- .container -->

<!-- nhúng phần footer -->
<?php include('widget/_footer.php') ?>

<!-- script dùng chung -->
<?php include('widget/_script.php') ?>
<!-- script -->
<script src="assets/dest/js/custom2.js"></script>
<script>
    $(document).ready(function ($) {
        $(window).scroll(function () {
                if ($(this).scrollTop() > 150) {
                    $(".header-bottom").addClass('fixNav')
                } else {
                    $(".header-bottom").removeClass('fixNav')
                }
            }
        )
    })
</script>
</body>
</html>
