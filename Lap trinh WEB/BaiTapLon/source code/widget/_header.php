<?php
require 'database/CategoryDAO.php';
require 'database/CompanyInfoDAO.php';
$categoryDAO = new CategoryDAO();
$companyInfoDAO = new CompanyInfoDAO();
?>
<div id="header">
    <div class="header-top">
        <div class="container">
            <div class="pull-left auto-width-left">
                <ul class="top-menu menu-beta l-inline">
                    <?php
                    $companyInfo = $companyInfoDAO->getCompanyInfo();
                    echo "<li><a href=''><i class='fa fa-home'></i> $companyInfo[address]</a></li>";
                    echo " <li><a href=''><i class='fa fa-phone'></i> $companyInfo[hotline]</a></li>";
                    ?>
                </ul>
            </div>
            <div class="pull-right auto-width-right">
                <ul class="top-details menu-beta l-inline">
                    <li><a href="#"><i class="fa fa-user"></i>Tài khoản</a></li>
                    <li><a href="#">Đăng kí</a></li>
                    <li><a href="#">Đăng nhập</a></li>
                </ul>
            </div>
            <div class="clearfix"></div>
        </div> <!-- .container -->
    </div> <!-- .header-top -->
    <div class="header-body">
        <div class="container beta-relative">
            <div class="pull-left">
                <a href="index.php" id="logo"><img src="assets/dest/images/logo-cake.png" width="200px" alt=""></a>
            </div>
            <div class="pull-right beta-components space-left ov">
                <div class="space10">&nbsp;</div>
                <div class="beta-comp">
                    <form role="search" method="get" id="searchform" action="search.php">
                        <input type="text" value="" name="key" id="s" placeholder="Nhập từ khóa..."/>
                        <button class="fa fa-search" type="submit" id="searchsubmit"></button>
                    </form>
                </div>

                <div class="beta-comp">
                    <div id="header_cart" class="cart">
                        <div class="beta-select"><i class="fa fa-shopping-cart"></i> Giỏ hàng (<span id="header_cart_total_quantity">Trống</span>) <i
                                    class="fa fa-chevron-down"></i></div>
<!--                        <div class="beta-dropdown cart-body">-->
<!--                            <div class="cart-item">-->
<!--                                <div class="media">-->
<!--                                    <a class="pull-left" href="#"><img src="assets/dest/images/products/cart/1.png"-->
<!--                                                                       alt=""></a>-->
<!--                                    <div class="media-body">-->
<!--                                        <span class="cart-item-title">Sample Woman Top</span>-->
<!--                                        <span class="cart-item-options">Size: XS; Colar: Navy</span>-->
<!--                                        <span class="cart-item-amount">1*<span>$49.50</span></span>-->
<!--                                    </div>-->
<!--                                </div>-->
<!--                            </div>-->
<!---->
<!--                            <div class="cart-item">-->
<!--                                <div class="media">-->
<!--                                    <a class="pull-left" href="#"><img src="assets/dest/images/products/cart/2.png"-->
<!--                                                                       alt=""></a>-->
<!--                                    <div class="media-body">-->
<!--                                        <span class="cart-item-title">Sample Woman Top</span>-->
<!--                                        <span class="cart-item-options">Size: XS; Colar: Navy</span>-->
<!--                                        <span class="cart-item-amount">1*<span>$49.50</span></span>-->
<!--                                    </div>-->
<!--                                </div>-->
<!--                            </div>-->
<!---->
<!--                            <div class="cart-item">-->
<!--                                <div class="media">-->
<!--                                    <a class="pull-left" href="#"><img src="assets/dest/images/products/cart/3.png"-->
<!--                                                                       alt=""></a>-->
<!--                                    <div class="media-body">-->
<!--                                        <span class="cart-item-title">Sample Woman Top</span>-->
<!--                                        <span class="cart-item-options">Size: XS; Colar: Navy</span>-->
<!--                                        <span class="cart-item-amount">1*<span>$49.50</span></span>-->
<!--                                    </div>-->
<!--                                </div>-->
<!--                            </div>-->
<!---->
<!--                            <div class="cart-caption">-->
<!--                                <div class="cart-total text-right">Tổng tiền: <span-->
<!--                                            class="cart-total-value">$34.55</span></div>-->
<!--                                <div class="clearfix"></div>-->
<!---->
<!--                                <div class="center">-->
<!--                                    <div class="space10">&nbsp;</div>-->
<!--                                    <a href="checkout.php" class="beta-btn primary text-center">Đặt hàng <i-->
<!--                                                class="fa fa-chevron-right"></i></a>-->
<!--                                </div>-->
<!--                            </div>-->
<!--                        </div>-->
                    </div> <!-- .cart -->
                </div>
            </div>
            <div class="clearfix"></div>
        </div> <!-- .container -->
    </div> <!-- .header-body -->
    <div class="header-bottom" style="background-color: #0277b8;">
        <div class="container">
            <a class="visible-xs beta-menu-toggle pull-right" href="#"><span class='beta-menu-toggle-text'>Menu</span>
                <i class="fa fa-bars"></i></a>
            <div class="visible-xs clearfix"></div>
            <nav class="main-menu">
                <ul class="l-inline ov">
                    <li <?php if (strpos($_SERVER['REQUEST_URI'],'index.php') !== false) echo "class='active'"; ?>><a href="index.php">Trang chủ</a></li>
                    <li <?php if (strpos($_SERVER['REQUEST_URI'],'category.php') !== false) echo "class='active'"; ?>><a href="#">Sản phẩm</a>
                        <ul class="sub-menu">
                            <?php
                            $categories = $categoryDAO->getListCategories();
                            foreach ($categories as $category) {
                                echo "<li><a href='category.php?categoryId=$category[id]'>$category[name]</a></li>";
                            }
                            ?>
                        </ul>
                    </li>
                    <li <?php if (strpos($_SERVER['REQUEST_URI'],'about.php') !== false) echo "class='active'"; ?>><a href="about.php">Giới thiệu</a></li>
                    <li <?php if (strpos($_SERVER['REQUEST_URI'],'contacts.php') !== false) echo "class='active'"; ?>><a href="contacts.php">Liên hệ</a></li>
                </ul>
                <div class="clearfix"></div>
            </nav>
        </div> <!-- .container -->
    </div> <!-- .header-bottom -->
</div> <!-- #header -->
