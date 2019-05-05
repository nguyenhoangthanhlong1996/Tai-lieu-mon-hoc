//functions
function encodeImagetoBase64(element, showView) {
	var filesSelected = element.files;
	if (filesSelected.length > 0) {
		var file = filesSelected[0];
		if (file.type.match('image.*')) {
			var reader = new FileReader();
			reader.readAsDataURL(file);
			reader.onload = function (e) {
				var image = new Image();
				image.onload = function (imageEvent) {
					// Resize the image using canvas  
					var canvas = document.createElement('canvas'),
						max_size = 800,// TODO : max size for a pic  
						width = image.width,
						height = image.height;
					//check size
					if (height < 350 || width < 350) {
						$('#fcategoryImage').val(null);
						Swal.fire({
							type: 'error',
							title: 'Lỗi chọn hình tải lên',
							text: 'Bạn chọn tập tin hình có kích thước lớn hơn 350px'
						});
						return;
					} else {
						if (width > height) {
							if (width > max_size) {
								height *= max_size / width;
								width = max_size;
							}
						} else {
							if (height > max_size) {
								width *= max_size / height;
								height = max_size;
							}
						}
						canvas.width = width;
						canvas.height = height;
						canvas.getContext('2d').drawImage(image, 0, 0, width, height);
						//Getting base64 string;  
						var dataUrl = canvas.toDataURL('image/jpeg');
						$("#" + showView).attr("src", dataUrl);
						$('#categoryImage').val(dataUrl);
						$('#lbl_deleteCategoryImage').show();
					}
				}
				image.src = e.target.result;
			}
		}
	}
}

function getCategoryById(categoryId) {
	try {
		$.ajax({
			type: "get",
			url: "/api/getCategory/" + categoryId,
			data: "",
			success: function (response) {
				if (response.code == "200") {
					var obj = response.data;
					$('#categoryName').val(obj.categoryName);
					$('#categoryNbr').val(obj.categoryNbr);
					$('#categoryDesc').val(obj.categoryDesc);
					if (obj.categoryEnable) {
						$('#categoryEnable').prop('checked', true);
					} else {
						$('#categoryEnable').prop('checked', false);
					}
					if (obj.categoryImage) {
						$('#icategoryImage').attr('src', obj.categoryImage);
						$('#categoryImage').val(obj.categoryImage);
						$('#lbl_deleteCategoryImage').show();
					} else {
						$('#icategoryImage').attr('src', 'data:image/jpeg;base64,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');
						$('#categoryImage').val(null);
					}
					$('#categoryId').val(obj.categoryId);
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
					text: "Không tải được thông tin danh mục"
				});
			}
		});
	} catch (error) {
		console.log(error.message);
	}
}

function deleteCategoryById(categoryId) {
	try {
		$.ajax({
			type: "post",
			url: "/api/deleteCategory",
			//contentType: "application/json",
			//dataType: "json",
			data: "id=" + categoryId,
			success: function (response) {
				if (response.code == "200") {
					Swal.fire(
						'kết quả hủy danh mục',
						'Danh mục bạn chọn đã được hủy',
						'success'
					);
					//remove
					var myTable = $('#lstCategories').DataTable();
					var _tr;
					$("#lstCategories tbody tr").each(function () {
						var _id = $(this).data('id');
						if (_id == categoryId) {
							_tr = $(this);
							return false;
						}
					});
					myTable.row(_tr).remove().draw(false);
				} else {
					Swal.fire({
						type: 'error',
						title: 'Lỗi hủy bỏ danh mục',
						text: response.msg
					});
				}
			},
			error: function () {
				Swal.fire({
					type: 'error',
					title: 'Lỗi hủy bỏ danh mục',
					text: 'Không thể thực hiện hủy bỏ danh mục đã chọn'
				});
			}
		});
	} catch (err) {
		console.log(err.message);
	}
}
function clearDataForm() {
	$('#categoryName').val('');
	$('#categoryNbr').val('');
	$('#categoryDesc').val('');
	$('#categoryId').val(0);
	$('#fcategoryImage').val(null);
	$('#categoryImage').val('');
	$('#icategoryImage').attr('src', 'data:image/jpeg;base64,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');
	$('#lbl_deleteCategoryImage').hide();
}
//end functions

$(document).ready(function () {
	//Validate
	$("#frmCategories").validate({
		ignore: "not:hidden",
		onfocusout: false,
		onkeyup: false,
		onclick: false,
		rules: {
			categoryName: "required",
			categoryNbr: "required",
			categoryDesc: "required",
		},
		messages: {
			categoryName: "Nhập tên danh mục",
			categoryNbr: "Nhập mã định danh",
			categoryDesc: "Nhập mã mô tả"
		}
	});
	//end validate
	//Load table
	$('#lstCategories').DataTable({
		"columnDefs": [{
			"targets": 'no-sort',
			"orderable": false,
		}],
		"aaSorting": [[2, "asc"]]
	});
	//Events
	$(document).on('click', '#btn_edit', function () {
		var categoryId = $(this).attr("title");
		if (categoryId) {
			getCategoryById(categoryId);
		}
	});
	$(document).on('click', '#btn_delete', function () {
		var categoryId = $(this).attr("title");
		if (categoryId) {
			Swal.fire({
				title: 'Bạn có chắc muốn hủy danh mục này?',
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
					deleteCategoryById(categoryId);
				}
			})
		}
	});
	$(document).on('click', '#lbl_deleteCategoryImage', function () {
		if ($('#categoryImage').val()) {
			$('#fcategoryImage').val(null);
			$('#categoryImage').val('');
			$('#icategoryImage').attr('src', 'data:image/jpeg;base64,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');
			$(this).hide();
		}
	});
	$('#bnt_Reset').click(function () {
		clearDataForm();
	});
	//submit
	$("#frmCategories").submit(function (event) {
		event.preventDefault();
		if (!$("#frmCategories").valid()) {
			return;
		}
		//
		try {
			$.ajax({
				type: "post",
				url: "/categories",
				data: $('form[name=frmCategories]').serialize(),
				success: function (response) {
					var responseCode = response.code;
					var responseMsg = response.msg;
					if (responseCode == "200") {
						Swal.fire({
							type: 'success',
							title: 'Thông tin đã được lưu',
							showConfirmButton: false,
							timer: 1500
						});
						//update list
						var responseData = response.data;
						if (responseData) {
							var categoryId = responseData.categoryId;
							var table = $('#lstCategories').DataTable();
							var _tr;
							var _data;
							//Modify
							if (responseMsg == "update") {
								//Find td
								$("#lstCategories tbody tr").each(function () {
									var _id = $(this).data('id');
									if (_id == categoryId) {
										_tr = $(this);
										return false;
									}
								});
								//
								_data = table.row(_tr).data();
								if (responseData.categoryEnable) {
									_data[1] = '<input type="checkbox" disabled="disabled" checked="checked">';
								} else {
									_data[1] = '<input type="checkbox" disabled="disabled">';
								}
								_data[2] = responseData.categoryNbr;
								_data[3] = responseData.categoryName;
								table.row(_tr).data(_data).draw();
							} else {
								//Add new
								_data = [];
								if (responseData.categoryEnable) {
									_data[1] = '<input type="checkbox" disabled="disabled" checked="checked">';
								} else {
									_data[1] = '<input type="checkbox" disabled="disabled">';
								}
								_data[2] = responseData.categoryNbr;
								_data[3] = responseData.categoryName;
								//Add group btn
								var _divbtnGroup = '<div class="btn-group">';
								_divbtnGroup += '<button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown">';
								_divbtnGroup += '<span class="caret"></span> <span class="sr-only">Tùy chọn</span>';
								_divbtnGroup += '</button>';
								_divbtnGroup += '<ul class="dropdown-menu" role="menu">';
								_divbtnGroup += '<li><a title="' + categoryId + '" id="btn_edit" href="#frmCategories">Chỉnh sửa</a></li>';
								_divbtnGroup += '<li class="divider"></li>';
								_divbtnGroup += '<li><a title="' + categoryId + '" id="btn_delete" href="#lstCategories">Hủy bỏ</a></li>';
								_divbtnGroup += '</ul>';
								_divbtnGroup += '</div>';
								_data[0] = _divbtnGroup;
								var rowNode = table.row.add(_data).draw(false).node();
								$(rowNode).attr('data-id', categoryId);
							}
							//Clear form
							clearDataForm();
						}
						//
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