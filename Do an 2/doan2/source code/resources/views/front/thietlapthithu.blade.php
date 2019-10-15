<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Tùy chọn</title>
    <link rel="stylesheet" href="{{asset('front/css/bootstrap.min.css')}}">
    <link rel="stylesheet" href="{{asset('front/css/style.css')}}">
</head>

<body>
    <div id="wrapper">
        <div class="jumbotron">
            <img src="{{asset('front/images/1004px-Logo-hcmut.svg.png')}}" class="logo" alt="">
            <h1 class="display-4 ">Thư viện đề thi trắc nghiệm Tiếng Anh</h1>
            <hr class="my-4">
            <form method="post" action="{{route('front.doExam')}}">
                @csrf
                <div class="row">
                    <select name="optionLevel" class="browser-default custom-select custom-select-lg col-4 offset-4">
                        <option selected value="0">--Chọn cấp độ--</option>
                        @foreach ($categories as $category)
                            <option value="{{$category->id}}">{{$category->name}}</option>
                        @endforeach
                    </select>
                </div>
                <div class="row">
                    <button type="submit" class="btn btn-warning col-2 offset-5">Hoàn tất</button>
                </div>
            </form>
            <div class="row">
                <a href="{{route('front.home')}}" class="col-12">Quay về trang chủ</a>
            </div>
        </div>
    </div>
</body>

</html>