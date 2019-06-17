<?php
if (isset($_GET['productId'])) {
    $productId = $_GET['productId'];
    require 'database/ProductDAO.php';
    $productDAO = new ProductDAO();
    // Thông tin sản phẩm
    $product = $productDAO->getProductById($productId);
    if ($product == null) {
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

<!-- phần điều hướng trang -->
<div class="inner-header">
    <div class="container">
        <div class="pull-left">
            <h6 class="inner-title">Chi tiết sản phẩm</h6>
        </div>
        <div class="pull-right">
            <div class="beta-breadcrumb font-large">
                <a href="index.php">Trang chủ</a> / <span>Chi tiết sản phẩm</span>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>

<!-- nội dung trang -->
<div class="container">
    <div id="content">
        <div class="row">
            <div class="col-sm-9">

                <div class="row">
                    <div class="col-sm-4">
                        <img src='<?php echo $product['cover_image']; ?>' alt="">
                    </div>
                    <div class="col-sm-8">
                        <div class="single-item-body">
                            <p class="single-item-title"
                               style="font-size: 1.5rem; font-weight: bold; color: #0277b8;"><?php echo $product['name']; ?></p>
                            <div class="space20">&nbsp;</div>
                            <p class="single-item-price">
                                <span><?php echo number_format($product['price'], 0, '', ','); ?> VNĐ</span>
                            </p>
                        </div>
                        <div class="clearfix"></div>
                        <div class="space20">&nbsp;</div>
                        <p>Đơn vị tính: <span style="font-weight: bold;"><?php echo $product['unit_name']; ?></span></p>
                        <p>Số lượng hiện có: <span
                                    style="font-weight: bold;"><?php echo $product['quantity_remaining']; ?></span></p>
                        <p>Tình
                            trạng: <?php echo $product['out_of_stock_flag'] ? "<span style='color: darkred; font-weight: bold; font-style: italic;'>HẾT HÀNG</span>" : "<span style='color: blue; font-weight: bold; font-style: italic;'>CÒN HÀNG</span>" ?></p>
                        <?php $tags = preg_split('/,/', $product['tags'], null, PREG_SPLIT_NO_EMPTY); ?>
                        <p>Tags: <span style="font-weight: bold; font-style: italic;">
                                <?php
                                foreach ($tags as $tag) {
                                    $tag = trim($tag);
                                    echo "<a href='search.php?key=$tag'>$tag</a>, ";
                                };
                                ?>
                            </span></p>
                        <div class="single-item-desc">
                            <p>Mô tả: <span style="font-style: italic;"><?php echo $product['description']; ?></span>
                            </p>
                        </div>
                        <div class="space20">&nbsp;</div>

                        <p>Số lượng:</p>
                        <div class="single-item-options">
                            <input type="number" class="wc-select" value="1"
                                   style="font-size: 18px; font-weight: bold; text-align: center;">
                            <a class="add-to-cart" href="#"><i class="fa fa-shopping-cart"></i></a>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>

                <div class="space40">&nbsp;</div>
                <div class="woocommerce-tabs">
                    <ul class="tabs">
                        <li><a href="#tab-description">Mô tả</a></li>
                        <li><a href="#tab-reviews">Đánh giá (0)</a></li>
                    </ul>

                    <div class="panel" id="tab-description">
                        <?php echo $product['posts']; ?>
                    </div>
                    <div class="panel" id="tab-reviews">
                        <p>Không có đánh giá</p>
                    </div>
                </div>
                <div class="space50">&nbsp;</div>
                <div class="beta-products-list">
                    <h4>Sản phẩm liên quan</h4>
                    <div class="space20">&nbsp;</div>
                    <div class="row">
                        <?php
                        $listRelatedProducts = $productDAO->getListRelatedProducts($productId, 3);
                        foreach ($listRelatedProducts as $product) {
                            $id = $product['id'];
                            $name = $product['name'];
                            $price = number_format($product['price'], 0, '', ',');
                            $image = $product['cover_image'];
                            echo "
                                <div class='col-sm-4' style='margin-top: 50px;'>
                                    <div class='single-item'>
                                        <div class='single-item-header'><a href='product_detail.php'><img src='$product[cover_image]' style='width: 270px; height: 270px;' alt=''></a></div>
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
            <div class="col-sm-3 aside">
                <div class="widget">
                    <h3 class="widget-title">Sản phẩm bán chạy</h3>
                    <div class="widget-body">
                        <div class="beta-sales beta-lists">
                            <?php
                            $listBestSellerProducts = $productDAO->getListBestSellerProducts(4);
                            foreach ($listBestSellerProducts as $product) {
                                $id = $product['id'];
                                $name = $product['name'];
                                $price = number_format($product['price'], 0, '', ',');
                                $image = $product['cover_image'];
                                echo "<div class='media beta-sales-item'>
                                        <a class='pull-left' href='product_detail.php?productId=$id'><img
                                                    src='$image' style='width: 60px; height: 60px;' alt=''></a>
                                        <div class='media-body'>$name
                                            <span class='beta-sales-price'>$price</span>
                                        </div>
                                    </div>";
                            }
                            ?>
                        </div>
                    </div>
                </div> <!-- best sellers widget -->
                <div class="widget">
                    <h3 class="widget-title">Sản phẩm mới</h3>
                    <div class="widget-body">
                        <div class="beta-sales beta-lists">
                            <?php
                            $listNewProducts = $productDAO->getListNewProducts(4);
                            foreach ($listNewProducts as $product) {
                                $id = $product['id'];
                                $name = $product['name'];
                                $price = number_format($product['price'], 0, '', ',');
                                $image = $product['cover_image'];
                                echo "<div class='media beta-sales-item'>
                                        <a class='pull-left' href='product_detail.php?productId=$id'><img
                                                    src='$image' style='width: 60px; height: 60px;' alt=''></a>
                                        <div class='media-body'>$name
                                            <span class='beta-sales-price'>$price</span>
                                        </div>
                                    </div>";
                            }
                            ?>
                        </div>
                    </div>
                </div> <!-- best sellers widget -->
            </div>
        </div>
    </div> <!-- #content -->
</div> <!-- .container -->

<!-- nhúng phần footer -->
<?php include('widget/_footer.php') ?>

<!-- script dùng chung -->
<?php include('widget/_script.php') ?>

</body>
</html>
