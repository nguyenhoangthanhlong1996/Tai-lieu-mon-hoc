@extends('admin.master') {{-- Thêm CSS --}} 
@section('css')
<link rel="stylesheet" href="{{asset('admin/css/select.css')}}">
@endsection
 {{-- Thêm JS --}} 
@section('js')
<script src="{{asset('admin/js/slide-up-alert.js')}}"></script>
<script src="{{asset('admin/js/jquery.uniform.js')}}"></script>
<script src="{{asset('admin/js/select.min.js')}}"></script>
<script src="{{asset('admin/js/jquery.dataTables.min.js')}}"></script>
<script src="{{asset('admin/js/matrix.tables.js')}}"></script>
<script src="{{asset('admin/js/dialog-delete.js')}}"></script>
@endsection
 {{-- Tiêu đề --}} 
@section('title')
<h1><i class="fas fa-list-alt"></i> Tài khoản</h1>
@endsection
 {{-- Nội dung chính --}} 
@section('content')
<hr>
<div class="row-fluid">
	@include('admin.widgets.alert',['slideUp' => true])
	<div class="span12">
		<div class="widget-box">
			<div class="widget-title">
				<span class="icon"><a href="{{route('admin.account.create')}}"><i class="fas fa-plus"></a></i></span>
				<h5>Danh sách tài khoản</h5>
			</div>
			<div class="widget-content nopadding">
				<table class="table table-bordered data-table">
					@php 
					$isSuperAdmin = Auth::user()->isSuperAdmin(); 
					@endphp
					<thead>
						<tr>
							<th>STT</th>
							<th>Tên tài khoản</th>
							<th>Tên người dùng</th>
							<th>Quyền hạn</th>
							<th>Ngày tạo</th>
							<th>Ngày cập nhật</th>
							@if ($isSuperAdmin)
							<th>Chức năng</th>
							@endif
						</tr>
					</thead>
					<tbody>
						@php 
							$index = 1; 
						@endphp
						@if ($isSuperAdmin)
							@foreach ($users as $user)
								<tr class="gradeX">
									<td>{{$index++}}</td>
									<td>{{$user->username}}</td>
									<td>{{$user->name}}</td>
									<td>{{$user->permission->type}}</td>
									<td class="center">{{$user->created_at}}</td>
									<td class="center">{{$user->updated_at}}</td>
									<td class="center">
										<form class="form-delete" method="POST" action="{{route('admin.account.destroy', ['id' => $user->id])}}" >
											{{ csrf_field() }}
											@method('DELETE')
											@if ($user->isSuperAdmin())
												<a class="edit" href="{{route('admin.account.edit',['id'=>$user->id])}}"><i class="fas fa-user-edit" style="color:brown;"></i></a>
											@else
												<a class="edit" href="{{route('admin.account.edit',['id'=>$user->id])}}"><i class="fas fa-user-edit"></i></a>
												<a href="#" class="delete"><i class="fas fa-user-slash"></i></a>
											@endif
										</form>
									</td>
								</tr>
							@endforeach
						@else
						@foreach ($users as $user)
								<tr class="gradeX">
									<td>{{$index++}}</td>
									<td>{{$user->username}}</td>
									<td>{{$user->name}}</td>
									<td>{{$user->permission->type}}</td>
									<td class="center">{{$user->created_at}}</td>
									<td class="center">{{$user->updated_at}}</td>
								</tr>
							@endforeach
						@endif
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

@include('admin.widgets.dialog-delete', ['content' => 'Khi tài khoản này bị xóa thì tất cả các dữ liệu liên quan đến tài khoản này cũng bị mất theo. Bạn có muốn xóa không ?'])
@endsection