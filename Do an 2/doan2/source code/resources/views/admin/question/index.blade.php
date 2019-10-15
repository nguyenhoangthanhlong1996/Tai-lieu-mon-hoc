@extends('admin.master') 
{{-- Thêm CSS --}} 
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
<h1><i class="fas fa-list-alt"></i> Câu hỏi</h1>
@endsection
 {{-- Nội dung chính --}} 
@section('content')
<hr>
<div class="row-fluid">
	@include('admin.widgets.alert',['slideUp' => true])
	<div class="span12">
		<div class="widget-box">
			<div class="widget-title">
				<span class="icon"><a href="{{route('admin.question.create')}}"><i class="fas fa-plus"></a></i></span>
				<h5>Danh sách câu hỏi</h5>
			</div>
			<div class="widget-content nopadding">
				<table class="table table-bordered data-table">
					@php 
					$isAdmin = (Auth::user()->isSuperAdmin() || Auth::user()->isAdmin()); 
					@endphp
					<thead>
						<tr>
							<th>STT</th>
							<th>Nội dung câu hỏi</th>
							<th>Ngày tạo</th>
							<th>Ngày cập nhật</th>
							@if ($isAdmin)
							<th>Chức năng</th>
							@endif
						</tr>
					</thead>
					<tbody>
						@php 
							$index = 1; 
						@endphp
						@if ($isAdmin)
							@foreach ($questions as $question)
								<tr class="gradeX">
									<td>{{$index++}}</td>
									<td>{{$question->content}}</td>
									<td class="center">{{$question->created_at}}</td>
									<td class="center">{{$question->updated_at}}</td>
									<td class="center">
										<form class="form-delete" method="POST" action="{{route('admin.question.destroy', ['id' => $question->id])}}" >
											{{ csrf_field() }}
											@method('DELETE')
											<a class="edit" href="{{route('admin.question.edit',['id'=>$question->id])}}"><i class="fas fa-edit"></i></a>
											<a style="cursor: pointer;" class="delete"><i class="fas fa-trash-alt"></i></a>
										</form>
									</td>
								</tr>
							@endforeach
						@else
						@foreach ($questions as $question)
								<tr class="gradeX">
									<td>{{$index++}}</td>
									<td>{{$question->name}}</td>
									<td class="center">{{$question->created_at}}</td>
									<td class="center">{{$question->updated_at}}</td>
								</tr>
							@endforeach
						@endif
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

@include('admin.widgets.dialog-delete', ['content' => 'Khi câu hỏi này bị xóa thì tất cả các dữ liệu liên quan đến câu hỏi này cũng bị xóa theo. Bạn có muốn xóa không ?'])
@endsection