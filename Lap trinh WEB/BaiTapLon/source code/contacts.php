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
            <h6 class="inner-title">Liên hệ</h6>
        </div>
        <div class="pull-right">
            <div class="beta-breadcrumb font-large">
                <a href="index.php">Trang chủ</a> / <span>Liên hệ</span>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>

<!-- noi dung trang -->
<div class="beta-map">
    <div class="abs-fullwidth beta-map wow flipInX">
        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3919.4939278466654!2d106.65860431498523!3d10.773431162194477!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752ec3c161a3fb%3A0xef77cd47a1cc691e!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBCw6FjaCBraG9hIFRQLkhDTQ!5e0!3m2!1svi!2s!4v1560659535150!5m2!1svi!2s" width="900" height="600" frameborder="0" style="border:0" allowfullscreen></iframe>
    </div>
</div>
<div class="container">
    <div id="content" class="space-top-none">

        <div class="space50">&nbsp;</div>
        <div class="row">
            <div class="col-sm-12">
                <h2>Thông tin liên hệ</h2>
                <div class="space20">&nbsp;</div>

                <h6 class="contact-title">Địa chỉ</h6>
                <p><?php echo $companyInfo['address']; ?></p>
                <div class="space20">&nbsp;</div>
                <h6 class="contact-title">Số điện thoại</h6>
                <p><?php echo $companyInfo['hotline']; ?></p>
                <div class="space20">&nbsp;</div>
                <h6 class="contact-title">Email</h6>
                <p><?php echo $companyInfo['email']; ?></p>
            </div>
        </div>
    </div> <!-- #content -->
</div> <!-- .container -->

<!-- nhúng phần footer -->
<?php include('widget/_footer.php') ?>

<!-- script dùng chung -->
<?php include('widget/_script.php') ?>
<!--customjs-->
<script type="text/javascript">
    $(function () {
        // this will get the full URL at the address bar
        var url = window.location.href;

        // passes on every "a" tag
        $(".main-menu a").each(function () {
            // checks if its the same on the address bar
            if (url == (this.href)) {
                $(this).closest("li").addClass("active");
                $(this).parents('li').addClass('parent-active');
            }
        });
    });


</script>
<script>
    jQuery(document).ready(function ($) {
        'use strict';

// color box

//color
        jQuery('#style-selector').animate({
            left: '-213px'
        });

        jQuery('#style-selector a.close').click(function (e) {
            e.preventDefault();
            var div = jQuery('#style-selector');
            if (div.css('left') === '-213px') {
                jQuery('#style-selector').animate({
                    left: '0'
                });
                jQuery(this).removeClass('icon-angle-left');
                jQuery(this).addClass('icon-angle-right');
            } else {
                jQuery('#style-selector').animate({
                    left: '-213px'
                });
                jQuery(this).removeClass('icon-angle-right');
                jQuery(this).addClass('icon-angle-left');
            }
        });
    });
</script>
</body>
</html>
