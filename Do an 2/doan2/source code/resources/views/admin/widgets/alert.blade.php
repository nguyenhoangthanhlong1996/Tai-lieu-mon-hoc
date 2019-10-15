@if (session('level_message') && session('content_message'))
<div class="alert alert-{{session('level_message')}} {{$slideUp ? 'slideUpAlert': ''}}">
    <h5>{{ session('content_message') }}</h5>
</div>
@endif