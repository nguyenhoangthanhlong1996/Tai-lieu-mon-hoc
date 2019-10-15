<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Làm bài</title>
    <link rel="stylesheet" href="{{asset('front/css/bootstrap.min.css')}}">
    <link rel="stylesheet" href="{{asset('front/css/style.css')}}">
    <link rel="stylesheet" href="{{asset('front/css/lambai.css')}}">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
</head>

<body>
    <div id="wrapper">
        <form id="question" class="jumbotron" method="POST" action="{{route('front.result')}}">
            @csrf
            <input id="time" type="hidden" value="{{$time}}">
            <div><i id="countdowntimer" class="far fa-clock fa-2x" style="float: right"></i></div>
            <div class="list-questions">
                @php
                $index = 1;
                $totalQuestion = count($questions);
                @endphp
                @foreach ($questions as $question)
                <div id="{{$question->id}}" index={{$index}} class="item-question">
                    <div class="row">
                        <h5>Câu hỏi {{$index++}} / {{$totalQuestion}}</h5>
                    </div>
                    <div class="row">
                        <p class="content">{{$question->content}}</p>
                    </div>
                    <hr>
                    @php
                    $answers = $question->answers;
                    @endphp
                    <input type="hidden" name="{{$question->id}}" value="0" />
                    @foreach ($answers as $answer)
                    {{-- <input type="radio" name="{{$question->id}}" value="{{$answer->id}}"> {{$answer->content}}</label><br> --}}
                    <label><input type="radio" name="{{$question->id}}" value="{{$answer->id}}"> {{$answer->content}}</label><br>
                    @endforeach
                </div>
                @endforeach
            </div>
            <nav>
                <ul class="pagination row" id="navigate-question">
                </ul>
            </nav>
            <button id="btnSubmit" type="submit" class="btn btn-warning">Nộp bài</button>
            <div class="row">
                <a href="{{route('front.home')}}" class="col-12">Quay về trang chủ</a>
            </div>
        </form>
    </div>

    <script src="{{asset('front/js/jquery-3.4.0.min.js')}}"></script>
    <script src="{{asset('front/js/bootstrap.min.js')}}"></script>
    <script src="{{asset('front/js/lambai.js')}}"></script>
</body>

</html>