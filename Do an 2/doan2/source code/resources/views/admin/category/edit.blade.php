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
@endsection
 {{-- Tiêu đề --}} 
@section('title')
<h1><i class="fas fa-edit"></i></i> Chuyên mục</h1>
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
				<span class="icon"><a href="{{route('admin.category.index')}}"><i class="fas fa-list-alt"></i></a></span>
				<h5>Thông tin chuyên mục</h5>
			</div>
			<div class="widget-content nopadding">
				<form action="{{route('admin.category.update',['id'=>$category->id])}}" method="post" class="form-horizontal">
					{{ csrf_field() }} @method("PUT")
					<div class="control-group">
						<label class="control-label">Tên chuyên mục </label>
						<div class="controls">
							<input type="text" name="name" value="{{old('name') ? old('name') : $category->name}}" class="span5" placeholder="Nhập tên chuyên mục"/>
						</div>
					</div>
					<div class="form-actions">
						<button type="submit" class="btn btn-success">Cập nhật chuyên mục</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

@endsection