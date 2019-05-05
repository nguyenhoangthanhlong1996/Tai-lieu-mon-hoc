//function
function clearDataForm() {
	$('#courseName').val('');
	$('#courseNbr').val('');
	$('#courseLearningHour').val('');
	$('#coursePrice').val('');
	$('#courseId').val(0);
	CKEDITOR.instances['courseDesc'].setData('');
}
function getCourseById(courseId) {
	try {
		$.ajax({
			type: "get",
			url: "/api/getCourse/" + courseId,
			data: "",
			success: function (response) {
				if (response.code == "200") {
					var obj = response.data;
					$('#courseName').val(obj.courseName);
					$('#courseNbr').val(obj.courseNbr);
					$('#courseLearningHour').val(obj.courseLearningHour);
					$('#coursePrice').val(obj.coursePrice);
					$('#courseCategory').val(obj.courseCategory.categoryId);
					CKEDITOR.instances['courseDesc'].setData(obj.courseDesc);
					if (obj.courseEnable) {
						$('#courseEnable').prop('checked', true);
					} else {
						$('#courseEnable').prop('checked', false);
					}
					$('#courseId').val(obj.courseId);
				} else {
					Swal.fire({
						type: 'error',
						title: 'Lỗi tải dữ liệu',
						text: response.msg
					});
				}
			},
			error: function () {
				Swal.fire({
					type: 'error',
					title: 'Lỗi tải dữ liệu',
					text: "Không tải được thông tin khóa học"
				});
			}
		});
	} catch (error) {
		console.log(error.message);
	}
}
function deleteCourseById(courseId) {
	try {
		$.ajax({
			type: "post",
			url: "/api/deleteCourse",
			data: "id=" + courseId,
			success: function (response) {
				if (response.code == "200") {
					Swal.fire(
						'kết quả hủy khóa học',
						'Khóa học bạn chọn đã được hủy',
						'success'
					);
					//remove
					var myTable = $('#lstCourses').DataTable();
					var _tr;
					$("#lstCourses tbody tr").each(function () {
						var _id = $(this).data('id');
						if (_id == courseId) {
							_tr = $(this);
							return false;
						}
					});
					myTable.row(_tr).remove().draw(false);
				} else {
					Swal.fire({
						type: 'error',
						title: 'Lỗi hủy bỏ khóa học',
						text: response.msg
					});
				}
			},
			error: function () {
				Swal.fire({
					type: 'error',
					title: 'Lỗi hủy bỏ khóa học',
					text: 'Không thể thực hiện hủy bỏ khóa học đã chọn'
				});
			}
		});
	} catch (err) {
		console.log(err.message);
	}
}
//end funtion
//main
$(document).ready(function () {
	if (CKEDITOR.env.ie && CKEDITOR.env.version < 9)
		CKEDITOR.tools.enableHtml5Elements(document);
	//CKEDITOR.config.height = 150;
	//CKEDITOR.config.width = 'auto';
	CKEDITOR.config.language = 'vi';
	CKEDITOR.config.toolbarCanCollapse = true;
	CKEDITOR.config.autoParagraph = false;
	var initCkeditor = (function () {
		var wysiwygareaAvailable = isWysiwygareaAvailable();
		return function () {
			var editorElement = CKEDITOR.document.getById('courseDesc');
			if (wysiwygareaAvailable) {
				CKEDITOR.replace('courseDesc', { toolbarStartupExpanded: false });
			} else {
				editorElement.setAttribute('contenteditable', 'true');
				CKEDITOR.inline('courseDesc');
			}
		};
		function isWysiwygareaAvailable() {
			if (CKEDITOR.revision == ('%RE' + 'V%')) {
				return true;
			}
			return !!CKEDITOR.plugins.get('wysiwygarea');
		}
	})();
	//Editor init
	initCkeditor();
	CKEDITOR.instances['courseDesc'].on('change', function () {
		var valueOfTextAreaOne = CKEDITOR.instances['courseDesc'].getData();
		$('#courseDesc').val(valueOfTextAreaOne);
	});
	//Validate
	$("#frmCourses").validate({
		ignore: "not:hidden",
		onfocusout: false,
		onkeyup: false,
		onclick: false,
		rules: {
			courseName: "required",
			courseCategory: "required",
			courseNbr: "required",
			courseLearningHour: "required",
			coursePrice: {
				required: true,
				number: true
			}
		},
		messages: {
			courseName: "Nhập tên khóa học",
			courseCategory: "Chọn danh mục khóa học",
			courseNbr: "Nhập mã định danh khóa học",
			courseLearningHour: "Nhập thời lượng khóa học",
			coursePrice: {
				required: "Nhập giá học phí khóa học",
				number: "Giá học phí khóa học phải là kiểu số"
			}
		}
	});
	//end validate
	//Load table
	$('#lstCourses').DataTable({
		"columnDefs": [{
			"targets": 'no-sort',
			"orderable": false,
		}],
		"aaSorting": [[2, "asc"]]
	});
	//Events
	$(document).on('click', '#btn_edit', function () {
		var courseId = $(this).attr("title");
		if (courseId) {
			getCourseById(courseId);
		}
	});
	$(document).on('click', '#btn_delete', function () {
		var courseId = $(this).attr("title");
		if (courseId) {
			Swal.fire({
				title: 'Bạn có chắc muốn hủy khóa học này?',
				text: "Bạn sẽ không thể khôi phục lại!",
				type: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: 'Đồng ý',
				cancelButtonText: 'Bỏ qua',
			}).then((result) => {
				if (result.value) {
					//deleted
					deleteCourseById(courseId);
				}
			})
		}
	});
	$('#bnt_Reset').click(function () {
		clearDataForm();
	});
	//submit
	$("#frmCourses").submit(function (event) {
		event.preventDefault();
		if (!$("#frmCourses").valid()) {
			return;
		}
		var value = CKEDITOR.instances['courseDesc'].getData();
		if (!value) {
			Swal.fire({
				type: 'error',
				title: 'Lỗi chưa nhập thông tin',
				text: 'Nhập mô tả chi tiết khóa học'
			});
			return;
		}
		//call ajax
		try {
			$.ajax({
				type: "post",
				url: "/courses",
				data: $('form[name=frmCourses]').serialize(),
				success: function (response) {
					if (response.code == "200") {
						Swal.fire({
							type: 'success',
							title: 'Thông tin đã được lưu',
							showConfirmButton: false,
							timer: 1500
						});
						//update list
						var responseData = response.data;
						if (responseData) {
							var courseId = responseData.courseId;
							var table = $('#lstCourses').DataTable();
							var _tr;
							var _data;
							//Modify
							if (response.msg == "update") {
								//Find td
								$("#lstCourses tbody tr").each(function () {
									var _id = $(this).data('id');
									if (_id == courseId) {
										_tr = $(this);
										return false;
									}
								});
								//
								_data = table.row(_tr).data();
								if (responseData.courseEnable) {
									_data[1] = '<input type="checkbox" disabled="disabled" checked="checked">';
								} else {
									_data[1] = '<input type="checkbox" disabled="disabled">';
								}
								_data[2] = responseData.courseNbr;
								_data[3] = responseData.courseName;
								table.row(_tr).data(_data).draw();
							} else {
								//Add new
								_data = [];
								if (responseData.courseEnable) {
									_data[1] = '<input type="checkbox" disabled="disabled" checked="checked">';
								} else {
									_data[1] = '<input type="checkbox" disabled="disabled">';
								}
								_data[2] = responseData.courseNbr;
								_data[3] = responseData.courseName;
								//Add group btn
								var _divbtnGroup = '<div class="btn-group">';
								_divbtnGroup += '<button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown">';
								_divbtnGroup += '<span class="caret"></span> <span class="sr-only">Tùy chọn</span>';
								_divbtnGroup += '</button>';
								_divbtnGroup += '<ul class="dropdown-menu" role="menu">';
								_divbtnGroup += '<li><a title="' + courseId + '" id="btn_edit" href="#frmCourses">Chỉnh sửa</a></li>';
								_divbtnGroup += '<li class="divider"></li>';
								_divbtnGroup += '<li><a title="' + courseId + '" id="btn_delete" href="#lstCourses">Hủy bỏ</a></li>';
								_divbtnGroup += '</ul>';
								_divbtnGroup += '</div>';
								_data[0] = _divbtnGroup;
								var rowNode = table.row.add(_data).draw(false).node();
								$(rowNode).attr('data-id', courseId);
							}
							//Clear form
							clearDataForm();
						}
					} else {
						//Show errorMessage
						var responseMsg = response.msg;
						if (responseMsg == "errors") {
							//Set error messages
							$.each(response.data, function (key, value) {
								$('input[name=' + key + ']').after('<span class="error">' + value + '</span>');
							});
							//
							Swal.fire({
								type: 'error',
								title: 'Lỗi lưu thông tin',
								text: 'Phát sinh lỗi trong quá trình lưu thông tin'
							});
						} else {
							Swal.fire({
								type: 'error',
								title: 'Lỗi lưu thông tin',
								text: responseMsg
							});
						}
					}
				},
				error: function () {
					Swal.fire({
						type: 'error',
						title: 'Lỗi lưu thông tin',
						text: 'Phát sinh lỗi trong quá trình lưu thông tin'
					});
				}
			});
		} catch (err) {
			console.log(err.message);
		}

	});
});