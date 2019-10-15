<!DOCTYPE html>
<html lang="en">

<head>
    <title>Matrix Admin</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="{{asset('admin/css/bootstrap.min.css')}}" />
    <link rel="stylesheet" href="{{asset('admin/css/bootstrap-responsive.min.css')}}" />
    <link rel="stylesheet" href="{{asset('admin/css/fullcalendar.css')}}" />
    <link rel="stylesheet" href="{{asset('admin/css/matrix-style.css')}}" />
    <link rel="stylesheet" href="{{asset('admin/css/matrix-media.css')}}" />
    <link href="{{asset('admin/font-awesome/css/font-awesome.css')}}" rel="stylesheet" />
    <link href="{{asset('admin/fontawesome/css/all.min.css')}}" rel="stylesheet" />
    <link rel="stylesheet" href="{{asset('admin/css/jquery.gritter.css')}}" />
    <link href="{{asset('admin/css/font-open-sans.css')}}" rel='stylesheet' type='text/css'> 
    @yield('css')
</head>

<body>
    @php
        $user = Auth::user();
    @endphp
    <!--Header-part-->
    <div id="header">
        <h1><a href="">Matrix Admin</a></h1>
    </div>
    <!--close-Header-part-->

    <!--top-Header-menu-->
    <div id="user-nav" class="navbar navbar-inverse">
        <ul class="nav">
            <li class="dropdown" id="profile-messages"><a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle"><i class="icon icon-user"></i> <span class="text">Welcome {{$user->name}}</span><b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="{{route('admin.account.edit',['id'=>$user->id])}}"><i class="icon-user"></i> Thay đổi thông tin</a></li>
                    <li class="divider"></li>
                    <li><a href="{{route('admin.login.create')}}"><i class="icon-key"></i> Đăng xuất</a></li>
                </ul>
            </li>
            <li class=""><a title="" href="{{route('admin.login.create')}}"><i class="icon icon-share-alt"></i> <span class="text">Đăng xuất</span></a></li>
        </ul>
    </div>
    <!--close-top-Header-menu-->
    <!--sidebar-menu-->
    <div id="sidebar"><a href="" class="visible-phone"><i class="icon icon-home"></i> Dashboard</a>
        <ul>
            <li class="{{Request::is('admin/home*') ? 'active' : ''}}"><a href="{{route('admin.home.index')}}"><i class="fas fa-home"></i> <span>Trang chủ</span></a> </li>
            <li class="submenu {{Request::is('admin/account*') ? 'active' : ''}}"> <a href=""><i class="fas fa-users"></i> <span>Tài khoản</span> <span class="right"><i class="fas fa-angle-down"></i></span> </a>
                <ul>
                    <li><a href="{{route('admin.account.index')}}"><i class="fas fa-list"></i> Danh sách</a></li>
                    @if ($user->isSuperAdmin())
                        <li><a href="{{route('admin.account.create')}}"><i class="fas fa-plus"></i> Thêm</a></li>
                    @endif
                </ul>
            </li>
            <li class="submenu {{Request::is('admin/category*') ? 'active' : ''}}"> <a href=""><i class="fas fa-bars"></i> <span>Chuyên mục</span> <span class="right"><i class="fas fa-angle-down"></i></span> </a>
                <ul>
                    <li><a href="{{route('admin.category.index')}}"><i class="fas fa-list"></i> Danh sách</a></li>
                    @if ($user->isSuperAdmin())
                        <li><a href="{{route('admin.category.create')}}"><i class="fas fa-plus"></i> Thêm</a></li>
                    @endif
                </ul>
            </li>
            <li class="submenu {{Request::is('admin/question*') ? 'active' : ''}}"> <a href=""><i class="fas fa-question"></i> <span>Câu hỏi</span> <span class="right"><i class="fas fa-angle-down"></i></span> </a>
                <ul>
                    <li><a href="{{route('admin.question.index')}}"><i class="fas fa-list"></i> Danh sách</a></li>
                    @if ($user->isSuperAdmin() || $user->isAdmin())
                        <li><a href="{{route('admin.question.create')}}"><i class="fas fa-plus"></i> Thêm</a></li>
                    @endif
                </ul>
            </li>
            <li class="content"> <span>Monthly Bandwidth Transfer</span>
                <div class="progress progress-mini progress-danger active progress-striped">
                    <div style="width: 77%;" class="bar"></div>
                </div>
                <span class="percent">77%</span>
                <div class="stat">21419.94 / 14000 MB</div>
            </li>
            <li class="content"> <span>Disk Space Usage</span>
                <div class="progress progress-mini active progress-striped">
                    <div style="width: 87%;" class="bar"></div>
                </div>
                <span class="percent">87%</span>
                <div class="stat">604.44 / 4000 MB</div>
            </li>
        </ul>
    </div>
    <!--sidebar-menu-->

    <!--main-container-part-->
    <div id="content">
        <!--breadcrumbs-->
        <div id="content-header">
            <div id="breadcrumb"> <a href="" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
            <div>@yield('title')</div>
        </div>
        <!--End-breadcrumbs-->

        <!--Action boxes-->
        <div class="container-fluid">
            @yield('content')
        </div>
    </div>

    <!--end-main-container-part-->

    <!--Footer-part-->

    <div class="row-fluid">
        <div id="footer" class="span12"> 2013 &copy; Matrix Admin. Brought to you by <a href="">Themedesigner.in</a> </div>
    </div>

    <!--end-Footer-part-->

    <script src="{{asset('admin/js/jquery.min.js')}}"></script>
    <script src="{{asset('admin/js/jquery.ui.custom.js')}}"></script>
    <script src="{{asset('admin/js/bootstrap.min.js')}}"></script>
    <script src="{{asset('admin/js/matrix.js')}}"></script>
    @yield('js');
</body>

</html>