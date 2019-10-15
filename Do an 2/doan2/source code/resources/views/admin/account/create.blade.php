@extends('admin.master') {{-- Thêm CSS --}} 
@section('css')
<link rel="stylesheet" href="{{asset('admin/css/select.css')}}">
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
<h1><i class="fas fa-plus"></i> Tài khoản</h1>
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
				<form action="{{route('admin.account.store')}}" method="post" class="form-horizontal">
					{{ csrf_field() }}
					<div class="control-group">
						<label class="control-label">Tên tài khoản </label>
						<div class="controls">
							<input type="text" name="username" value="{{old('username')}}" class="span5" placeholder="Nhập tên tài khoản" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Tên người dùng </label>
						<div class="controls">
							<input type="text" name="name" value="{{old('name')}}" class="span5" placeholder="Nhập tên người sử dụng tài khoản này" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Quyền hạn </label>
						<div class="controls">
							<select name="id_permission" class="span5">
								@foreach ($permissions as $permission)
									<option value="{{$permission->id}}" {{$permission->id == old('id_permission') ? 'selected' : ''}}>{{$permission->type}}</option>
								@endforeach
							</select>
						</div>
					</div>
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
					<div class="form-actions">
						<button type="submit" class="btn btn-success">Tạo tài khoản</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
@endsection