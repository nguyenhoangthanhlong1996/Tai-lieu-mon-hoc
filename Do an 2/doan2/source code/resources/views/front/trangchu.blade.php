<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Trang chủ</title>
    <link rel="stylesheet" href="{{asset('front/css/bootstrap.min.css')}}">
    <link rel="stylesheet" href="{{asset('front/css/style.css')}}">
</head>

<body>
    <div id="wrapper">
        <div class="jumbotron">
            <img src="{{asset('front/images/1004px-Logo-hcmut.svg.png')}}" class="logo" alt="">
            <h1 class="display-4">Thư viện đề thi trắc nghiệm Tiếng Anh</h1>
            <hr class="my-4">
            <div class="row">
                <a href="{{route('front.option')}}" class="btn btn-warning btn-lg col-4 offset-4">Thi thử trắc nghiệm</a>
            </div>
        </div>
    </div>

</body>

</html>