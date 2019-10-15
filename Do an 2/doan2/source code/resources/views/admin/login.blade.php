<!DOCTYPE html>
<html lang="en">

<head>
    <title>Matrix Admin</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="{{asset('admin/css/bootstrap.min.css')}}" />
    <link rel="stylesheet" href="{{asset('admin/css/bootstrap-responsive.min.css')}}" />
    <link rel="stylesheet" href="{{asset('admin/css/matrix-login.css')}}" />
    <link href="{{asset('admin/font-awesome/css/font-awesome.css')}}" rel="stylesheet" />
    <link href="{{asset('admin/css/font-open-sans.css')}}" rel='stylesheet' type='text/css'>
</head>

<body>
    <div id="loginbox">
    @include('admin.widgets.errors-validate')
    @include('admin.widgets.alert',['slideUp' => true])
        <form id="loginform" class="form-vertical" method="POST" action="{{route('admin.login.store')}}">
            {{ csrf_field() }}
            <div class="control-group normal_text">
                <h3>Quản trị viên</h3>
            </div>
            <div class="control-group">
                <div class="controls">
                    <div class="main_input_box">
                        <span class="add-on bg_lg"><i class="icon-user"> </i></span><input name="username" type="text" placeholder="Tên tài khoản"/>
                    </div>
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <div class="main_input_box">
                        <span class="add-on bg_ly"><i class="icon-lock"></i></span><input name="password" type="password"
                            placeholder="Mật khẩu" />
                    </div>
                </div>
            </div>
            <div class="form-actions">
                <span class="pull-right"><input type="submit" class="btn btn-success" value="Đăng nhập">
        </div>
    </form>
</div>

<script src="{{asset('admin/js/jquery.min.js')}}"></script>
<script src="{{asset('admin/js/matrix.login.js')}}"></script>
<script src="{{asset('admin/js/slide-up-alert.js')}}"></script>
</body>

</html>