<!doctype html>
<html lang="en">
<head>
    <?php include('widget/_head.php'); ?>
</head>
<body>

<!-- nhúng phần header -->
<?php include('widget/_header.php') ?>

<section class="bg-gray">
    <div class="inner-header">
        <div class="container">
            <div class="pull-left">
                <h6 class="inner-title">Page Not Found</h6>
            </div>
            <div class="pull-right">
                <div class="beta-breadcrumb font-large">
                    <a href="#">Pages</a> / <span>Page 404</span>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>

    <div class="container">
        <div id="content" class="space-top-none space-bottom-none">
            <div class="abs-fullwidth bg-gray">
                <div class="space100">&nbsp;</div>
                <div class="space80">&nbsp;</div>
                <div class="container text-center">
                    <h2>Oops! Trang không được tìm thấy!</h2>
                    <div class="space50">&nbsp</div>
                    <a href="index.php" class="btn btn-primary" style="font-size: 25px;">Quay về trang chủ</a>
                </div>
                <div class="space100">&nbsp;</div>
                <div class="space100">&nbsp;</div>
            </div>
        </div> <!-- #content -->
    </div> <!-- .container -->
</section>


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
