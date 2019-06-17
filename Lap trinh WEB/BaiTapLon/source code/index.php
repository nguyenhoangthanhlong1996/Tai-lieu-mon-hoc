<!doctype html>
<html lang="en">
<head>
    <?php include('widget/_head.php'); ?>
</head>
<body>
<!-- nhúng phần header -->
<?php include('widget/_header.php') ?>
<?php
require 'database/ProductDAO.php';
$productDAO = new ProductDAO();
?>
<!-- phần slider -->
<div class="rev-slider">
    <div class="fullwidthbanner-container">
        <div class="fullwidthbanner">
            <div class="bannercontainer">
                <div class="banner">
                    <ul>
                        <!-- THE FIRST SLIDE -->
                        <li data-transition="boxfade" data-slotamount="20" class="active-revslide"
                            style="width: 100%; height: 100%; overflow: hidden; z-index: 18; visibility: hidden; opacity: 0;">
                            <div class="slotholder" style="width:100%;height:100%;" data-duration="undefined"
                                 data-zoomstart="undefined" data-zoomend="undefined" data-rotationstart="undefined"
                                 data-rotationend="undefined" data-ease="undefined" data-bgpositionend="undefined"
                                 data-bgposition="undefined" data-kenburns="undefined" data-easeme="undefined"
                                 data-bgfit="undefined" data-bgfitend="undefined" data-owidth="undefined"
                                 data-oheight="undefined">
                                <div class="tp-bgimg defaultimg" data-lazyload="undefined" data-bgfit="cover"
                                     data-bgposition="center center" data-bgrepeat="no-repeat" data-lazydone="undefined"
                                     src="image/slide/banner1.jpg" data-src="image/slide/banner1.jpg"
                                     style="background-color: rgba(0, 0, 0, 0); background-repeat: no-repeat; background-image: url('image/slide/banner1.jpg'); background-size: cover; background-position: center center; width: 100%; height: 100%; opacity: 1; visibility: inherit;">
                                </div>
                            </div>
                        </li>
                        <li data-transition="boxfade" data-slotamount="20" class="active-revslide"
                            style="width: 100%; height: 100%; overflow: hidden; z-index: 18; visibility: hidden; opacity: 0;">
                            <div class="slotholder" style="width:100%;height:100%;" data-duration="undefined"
                                 data-zoomstart="undefined" data-zoomend="undefined" data-rotationstart="undefined"
                                 data-rotationend="undefined" data-ease="undefined" data-bgpositionend="undefined"
                                 data-bgposition="undefined" data-kenburns="undefined" data-easeme="undefined"
                                 data-bgfit="undefined" data-bgfitend="undefined" data-owidth="undefined"
                                 data-oheight="undefined">
                                <div class="tp-bgimg defaultimg" data-lazyload="undefined" data-bgfit="cover"
                                     data-bgposition="center center" data-bgrepeat="no-repeat" data-lazydone="undefined"
                                     src="image/slide/banner2.jpg" data-src="image/slide/banner2.jpg"
                                     style="background-color: rgba(0, 0, 0, 0); background-repeat: no-repeat; background-image: url('image/slide/banner2.jpg'); background-size: cover; background-position: center center; width: 100%; height: 100%; opacity: 1; visibility: inherit;">
                                </div>
                            </div>
                    </ul>
                </div>
            </div>

            <div class="tp-bannertimer"></div>
        </div>
    </div>
</div>
<!--slider-->
</div>

<!-- nội dung trang -->
<div class="container">
    <div id="content" class="space-top-none">
        <div class="main-content">
            <div class="space20">&nbsp;</div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="beta-products-list">
                        <h4>Sản phẩm mới</h4>
                        <div class="row">
                            <?php
                            $listNewProducts = $productDAO->getListNewProducts(8);
                            foreach ($listNewProducts as $product) {
                                $id = $product['id'];
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
                    <div class="space50">&nbsp;</div>
                    <div class="beta-products-list">
                        <h4>Sản phẩm bán chạy</h4>
                        <div class="row">
                            <?php
                            $listBestSellerProducts = $productDAO->getListBestSellerProducts(8);
                            foreach ($listBestSellerProducts as $product) {
                                $id = $product['id'];
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

</body>
</html>

