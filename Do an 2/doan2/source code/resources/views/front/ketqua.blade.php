<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Kết quả</title>
    <link rel="stylesheet" href="{{asset('front/css/bootstrap.min.css')}}">
    <link rel="stylesheet" href="{{asset('front/css/all.min.css')}}">
    <link rel="stylesheet" href="{{asset('front/css/fonts.css')}}">
    <link rel="stylesheet" href="{{asset('front/css/ketqua.css')}}">
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="list-result col-8">
                @php
                $index = 1;
                $countTrue = 0;
                @endphp
                @foreach ($questions as $question)
                <div class="item-result">
                    <h5>Câu {{$index++}}</h5>
                    <p class="content">{{$question->content}}</p>
                    @php
                    $answers = $question->answers;
                    $correctAnswerId = $answers->where('correct',1)->first()->id;
                    @endphp
                    <ul>
                        @foreach ($answers as $answer)
                        <li class="{{$chooseAnswers[$question->id] == $answer->id ? 'strong-text' : ''}}">{{$answer->content}} {!!$correctAnswerId == $answer->id ? '<i class="fas fa-check"></i>' : ''!!}</li>
                        @php
                            if ($chooseAnswers[$question->id] == $answer->id && $answer->id == $correctAnswerId)
                                $countTrue++;
                        @endphp
                        @endforeach
                    </ul>
                </div>
                @endforeach
            </div>
            <div class="col-4">
                <div class="container" style="position: fixed; background-color: rgb(202, 202, 202); max-width: inherit;">
                    <div class="row">
                        <span class="col-4 font-weight-bold">Tô đậm</span>
                        <span class="col-8">Đáp án bạn chọn</span>
                    </div>
                    <div class="row">
                        <i class="fas fa-check col-4"></i>
                        <span class="col-8">Đáp án đúng</span>
                    </div>
                    <div class="row">
                        <span class="col-8">Tổng số câu hỏi</span>
                        <span class="col-4">{{count($questions)}}</span>
                    </div>
                    <div class="row">
                        <span class="col-8">Số câu đúng</span>
                        <span class="col-4">{{$countTrue}}</span>
                    </div>
                    <div class="row">
                        <span class="col-8">Số câu sai</span>
                        <span class="col-4">{{count($questions) - $countTrue}}</span>
                    </div>
                    <div class="row">
                        <a href="{{route('front.home')}}">Quay về trang chủ</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="{{asset('front/js/jquery-3.4.0.min.js')}}"></script>
    <script src="{{asset('front/js/ketqua.js')}}"></script>
</body>

</html>