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
		$("input[name='changePassword']").change(function() {
			if(this.checked) {
				$('.change-password').show();
			} else {
				$('.change-password').hide();
			}
		});
		$("input[name='changePassword']").trigger("change");

	});
</script>
@endsection
 {{-- Tiêu đề --}} 
@section('title')
<h1><i class="fas fa-user-edit"></i></i> Tài khoản</h1>
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
				<span class="icon"><a href="{{route('admin.account.index')}}"><i class="fas fa-list-alt"></i></a></span>
				<h5>Thông tin tài khoản</h5>
			</div>
			<div class="widget-content nopadding">
				<form action="{{route('admin.account.update',['id'=>$user->id])}}" method="post" class="form-horizontal">
					{{ csrf_field() }} @method("PUT")
					<div class="control-group">
						<label class="control-label">Tên tài khoản </label>
						<div class="controls">
							<input type="text" value="{{$user->username}}" class="span5" placeholder="Nhập tên tài khoản" disabled/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Tên người dùng </label>
						<div class="controls">
							<input type="text" name="name" value="{{old('name') ? old('name') : $user->name}}" class="span5" placeholder="Nhập tên người sử dụng tài khoản này"
							/>
						</div>
					</div>
					@php 
						$currUser = Auth::user(); 
					@endphp 
					@if ($currUser->isSuperAdmin() && $user->id != $currUser->id)
					<div class="control-group">
						<label class="control-label">Quyền hạn </label>
						<div class="controls">
							<select name="id_permission" class="span5">
									@foreach ($permissions as $permission)
										<option value="{{$permission->id}}" {{$permission->id == (old('id_permission') ? old('id_permission') : $user->id_permission) ? 'selected' : ''}}>{{$permission->type}}</option>
									@endforeach
								</select>
						</div>
					</div>
					@endif
					<div class="controls">
						<span class="checked"><input type="checkbox" name="changePassword" {{old('changePassword') == 'on' ? 'checked' : ''}} style="opacity: 0;"></span>Đổi mật khẩu
					</div>
					<div class="change-password">
						<div class="control-group">
							<label class="control-label">Mật khẩu</label>
							<div class="controls">
								<input type="password" name="password" class="span5" placeholder="Nhập mật khẩu" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Nhập lại mật khẩu</label>
							<div class="controls">
								<input type="password" name="retypePassword" class="span5" placeholder="Nhập lại mật khẩu đã điền ở trên" />
							</div>
						</div>
					</div>
					<div class="form-actions">
						<button type="submit" class="btn btn-success">Cập nhật tài khoản</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>


@endsection