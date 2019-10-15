@extends('admin.master') {{-- Thêm CSS --}} 
@section('css')
<link rel="stylesheet" href="{{asset('admin/css/select.css')}}">
<link rel="stylesheet" href="{{asset('admin/css/uniform.css')}}">
@endsection
 {{-- Thêm JS --}} 
@section('js')
<script src="{{asset('admin/js/slide-up-alert.js')}}"></script>
<script src="{{asset('admin/js/jquery.uniform.js')}}"></script>
<script src="{{asset('admin/js/select.min.js')}}"></script>
<script src="{{asset('admin/js/matrix.form_common.js')}}"></script>
<script>
	$(document).ready(function () {
		//Nhấn thêm đáp án
		$('button.add-new-answer').click(function() {
			$('div.list-item-answer').append($('#item-answer').html());
			//Đánh lại thứ tự các đáp án
			$('div.list-item-answer .item-answer').each(function(index) {
				$(this).find('input.answer').attr('name', `answers[${index}]`);
				$(this).find('input.check-correct').attr('name', `checkCorrects[${index}]`);
			});
		});
		//Nhấn xóa đáp án
		$('.list-item-answer').on('click', '.item-answer a.delete-answer',function(e){
			e.preventDefault();
			$(this).parent().remove();
			//Đánh lại thứ tự các đáp án
			$('div.list-item-answer .item-answer').each(function(index) {
				$(this).find('input.answer').attr('name', `answers[${index}]`);
				$(this).find('input.check-correct').attr('name', `checkCorrects[${index}]`);
			});
		});
		//Đánh dấu là đáp án đúng
		$('.item-answer input.check-correct').live('change', function() {
			if ($(this).is(':checked')) {
				$(this).parent('span').addClass('checked');
			} else {
				$(this).parent('span').removeClass('checked');
			}
		});
	});

</script>
@endsection
 {{-- Tiêu đề --}} 
@section('title')
<h1><i class="fas fa-edit"></i> Câu hỏi</h1>
@endsection
 {{-- Nội dung chính --}} 
@section('content')
<hr>
<div class="row-fluid">
	@include('admin.widgets.alert',['slideUp' => true])
	@include('admin.widgets.errors-validate')
	<div class="span12">
		<div class="widget-box">
			<div class="widget-title">
				<span class="icon"><a href="{{route('admin.question.index')}}"><i class="fas fa-list-alt"></i></a></span>
				<h5>Thông tin câu hỏi</h5>
			</div>
			<div class="widget-content">
				<form action="{{route('admin.question.update',['id'=>$question->id])}}" method="post" class="form-horizontal">
					{{ csrf_field() }}
					@method("PUT")
					<div class="control-group">
						<label class="control-label">Câu hỏi </label>
						<div class="controls">
							<select name="id_category" class="span5">
								@foreach ($categories as $category)
									@if (old('id_category'))
										<option value="{{$category->id}}" {{$category->id == old('id_category') ? 'selected' : ''}}>{{$category->name}}</option>
									@else
										<option value="{{$category->id}}" {{$category->id == $question->id_category ? 'selected' : ''}}>{{$category->name}}</option>
									@endif
								@endforeach
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Nội dung câu hỏi<br/> <span style="font-style: italic;">(Lưu ý: Dùng kí tự '_' để đại diện cho phần bỏ trống cần điền đáp án)</span></label>
						<div class="controls">
							<textarea name="content" rows="8" class="span10" placeholder="Nhập nội dung câu hỏi">{{old('content') ? old('content') : $question->content}}</textarea>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Ghi chú</label>
						<div class="controls">
							<textarea name="note" rows="2" class="span10" placeholder="Nhập nội dung ghi chú">{{old('note') ? old('note') : $question->note}}</textarea>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Các đáp án</label>
						<div class="controls">
							<button type="button" class="btn btn-info add-new-answer">Thêm đáp án</button>
						</div>
						<div class='list-item-answer'>
							@if (old('answers'))
								@for ($i = 0; $i < count((array)old('answers')); $i++)
									<div class="controls item-answer">
										<input type="text" name="answers[{{$i}}]" value="{{old("answers.$i")}}" class="span7" placeholder="Nhập nội dung đáp án">
										<input type="hidden" name="checkCorrects[{{$i}}]" value=0 class="span3 check-correct" style="opacity: 0;">
										<input type="checkbox" name="checkCorrects[{{$i}}]" value=1 class="span3 check-correct" style="opacity: 0;" {{old("checkCorrects.$i") == 1 ? 'checked' : ''}}>Đáp án đúng
										<a href="#" class="delete-answer" style="margin-left:15px;"><i class="fas fa-trash-alt"></i></a>
									</div>
								@endfor
							@elseif ($answers)
								@for ($i = 0; $i < count($answers); $i++)
									<div class="controls item-answer">
										<input type="text" name="answers[{{$i}}]" value="{{$answers[$i]->content}}" class="span7" placeholder="Nhập nội dung đáp án">
										<input type="hidden" name="checkCorrects[{{$i}}]" value=0 class="span3 check-correct" style="opacity: 0;">
										<input type="checkbox" name="checkCorrects[{{$i}}]" value=1 class="span3 check-correct" style="opacity: 0;" {{$answers[$i]->correct == 1 ? 'checked' : ''}}>Đáp án đúng
										<a href="#" class="delete-answer" style="margin-left:15px;"><i class="fas fa-trash-alt"></i></a>
									</div>
								@endfor
							@endif
						</div>
					</div>
					<div class="form-actions">
						<button type="submit" class="btn btn-success">Cập nhật</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

{{-- item answer --}}
<div id='item-answer' style="display: none;">
	<div class="controls item-answer">
		<input type="text" name="answers[]" class="span7 answer" placeholder="Nhập nội dung đáp án">
		<input type="hidden" name="checkCorrects[]" value=0 class="span3 check-correct" style="opacity: 0;">
		<input type="checkbox" name="checkCorrects[]" value=1 class="span3 check-correct" style="opacity: 0;">Đáp án đúng
		<a href="#" class="delete-answer" style="margin-left:15px;"><i class="fas fa-trash-alt"></i></a>
	</div>
</div>
@endsection