@if ($errors->any())
<div class="alert alert-danger">
    <ul>
        @foreach ($errors->all() as $error)
            <li><h5>{{ $error }}</h5></li>
        @endforeach
    </ul>
</div>
@endif 