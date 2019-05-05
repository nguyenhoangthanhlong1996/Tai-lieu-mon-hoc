//Register Page
//Functions
function getListTinh_TP() {
	try {
		$.ajax({
			type: "get",
			url: "/api/getDanhSachTinh_ThanhPho",
			data: "",
			cache: true,
			success: function (response) {
				$('#preloder').hide();
				if (response.code == "200") {
					$('#p_noiSinhTinhTpId').select2({
						placeholder: '-- Chọn --',
						data: response.data,
						allowClear: true
					});
					$('#p_dcttTinhTpId').select2({
						placeholder: '-- Chọn --',
						data: response.data,
						allowClear: true
					});
					$('#p_cmndNoiCapId').select2({
						placeholder: '-- Chọn --',
						data: response.data,
						allowClear: true
					});
				} else {
					swal({
						title: "Lỗi tải dữ liệu",
						text: response.msg,
						type: "error"
					});
				}
			},
			error: function () {
				swal({
					title: "Lỗi tải dữ liệu",
					text: "Không tải được danh sách Tỉnh/Thành phố",
					type: "error"
				});
			}
		});
	} catch (err) {
		console.log(err.message);
	}
}
// ////////////////////////////////
function getListQuan_Huyen(id) {
	try {
		$('#p_dcttQuanHuyenId').empty().append(
			'<option value="">-- Chọn --</option>');
		$.ajax({
			type: "get",
			url: "/api/getDanhSachQuan_Huyen?id=" + id,
			data: "",
			cache: true,
			success: function (response) {
				$('#preloder').hide();
				if (response.code == "200") {
					var resultSet = response.data;
					resultSet.push({
						id: 0,
						text: 'Khác'
					});
					$('#p_dcttQuanHuyenId').select2({
						placeholder: '-- Chọn --',
						data: resultSet,
						allowClear: true
					});
				} else {
					swal({
						title: "Lỗi tải dữ liệu",
						text: response.msg,
						type: "error"
					});
				}
			},
			error: function () {
				swal({
					title: "Lỗi tải dữ liệu",
					text: "Không tải được danh sách Quận/Huyện",
					type: "error"
				});
			}
		});
	} catch (err) {
		console.log(err.message);
	}
}
// //////////////////////
function getListPhuong_Xa(id) {
	try {
		$('#p_dcttPhuongXaId').empty().append(
			'<option value="">-- Chọn --</option>');
		$.ajax({
			type: "get",
			url: "/api/getDanhSachPhuong_Xa?id=" + id,
			data: "",
			cache: true,
			success: function (response) {
				$('#preloder').hide();
				if (response.code == "200") {
					var resultSet = response.data;
					resultSet.push({
						id: 0,
						text: 'Khác'
					});
					$('#p_dcttPhuongXaId').select2({
						placeholder: '-- Chọn --',
						data: resultSet,
						allowClear: true
					});
				} else {
					swal({
						title: "Lỗi tải dữ liệu",
						text: response.msg,
						type: "error"
					});
				}
			},
			error: function () {
				swal({
					title: "Lỗi tải dữ liệu",
					text: "Không tải được danh sách Quận/Huyện",
					type: "error"
				});
			}
		});
	} catch (err) {
		console.log(err.message);
	}
}
// ////////////
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
					var canvas = document.createElement('canvas'), max_size = 800, width = image.width, height = image.height;
					// check size
					if (height < 350 || width < 350) {
						swal({
							title: "Lỗi tải tập tin ảnh",
							text: "Bạn phải chọn tập tin hình có kích thước lớn hơn 350px",
							type: "error"
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
						canvas.getContext('2d').drawImage(image, 0, 0, width,
							height);
						// Getting base64 string;
						var dataUrl = canvas.toDataURL('image/jpeg');
						$("#" + showView).attr("src", dataUrl);
						if (showView == 'img_cmndMatTruoc') {
							$('#p_cmndMatTruoc').val(dataUrl);
						} else {
							$('#p_cmndMatSau').val(dataUrl);
						}
					}
				}
				image.src = e.target.result;
			}
		}
	}
	;
}
// /////////////////////////////////
$(document).ready(function () {
	$.ajaxSetup({
		beforeSend: function (xhr) {
			$('#preloder1').show();
		},
		complete: function (xhr, stat) {
			$('#preloder1').hide();
		},
		success: function (result, status, xhr) {
			$('#preloder1').hide();
		}
	});
	// Load data
	getListTinh_TP();
	$('#p_noiSinhTinhTpKhac').prop('disabled', 'disabled');
	// Events
	//
	$('#p_noiSinhTinhTpId').on(
		'select2:select',
		function (e) {
			var id = $('#p_noiSinhTinhTpId :selected')
				.val();
			if (id) {
				if (id == 81 || id == 65) {
					$('#p_noiSinhTinhTpKhac').prop(
						'disabled', '');
				} else {
					$('#p_noiSinhTinhTpKhac').prop(
						'disabled', 'disabled');
				}
			}
		});
	$('#p_noiSinhTinhTpId').on('select2:unselect', function (e) {
		$('#p_noiSinhTinhTpKhac').prop('disabled', 'disabled');
	});
	// Load Quan/Huyen
	$('#p_dcttTinhTpId').on('select2:select', function (e) {
		$("#p_dcttQuanHuyenId").val(null).trigger('change');
		$("#p_dcttPhuongXaId").val(null).trigger('change');
		var id = $('#p_dcttTinhTpId :selected').val();
		if (id) {
			getListQuan_Huyen(parseInt(id));
		}
	});
	$('#p_dcttTinhTpId').on('select2:unselect', function (e) {
		// Clear data
		$('#p_dcttQuanHuyenId').empty();
		$('#p_dcttPhuongXaId').empty();
		$("#p_dcttQuanHuyenId").val(null).trigger('change');
		$("#p_dcttPhuongXaId").val(null).trigger('change');
	});
	// Load Phuong/Xa
	$('#p_dcttQuanHuyenId').on('select2:select', function (e) {
		$("#p_dcttPhuongXaId").val(null).trigger('change');
		var id = $('#p_dcttQuanHuyenId :selected').val();
		if (id) {
			getListPhuong_Xa(parseInt(id));
		}
	});
	$('#p_dcttQuanHuyenId').on('select2:unselect', function (e) {
		// Clear data
		$('#p_dcttPhuongXaId').empty();
		$("#p_dcttPhuongXaId").val(null).trigger('change');
	});
	// Stop keyup
	$('#p_cmndSo').bind('copy paste cut', function (e) {
		e.preventDefault(); // disable cut,copy,paste
		swal({
			title: "Lỗi đăng ký!",
			text: "Chức năng cut,copy & paste đã vô hiệu hóa",
			type: "error"
		});
	});
	$('#p_cmndSo_1').bind('copy paste cut', function (e) {
		e.preventDefault(); // disable cut,copy,paste
		swal({
			title: "Lỗi đăng ký!",
			text: "Chức năng cut,copy & paste đã vô hiệu hóa",
			type: "error"
		});
	});
	// DCLL
	$('#p_dcttSoNhaDuong').keyup(function () {
		try {
			var p_tinhTp = $('#p_dcttTinhTpId').select2('data')[0].text;
			var p_huyen = $('#p_dcttQuanHuyenId').select2('data')[0].text;
			var p_xa = $('#p_dcttPhuongXaId').select2('data')[0].text;
			if ($('#p_dcttTinhTpId').val() && $('#p_dcttQuanHuyenId').val() && $('#p_dcttPhuongXaId').val()) {
				var p_soNha = $('#p_dcttSoNhaDuong').val();
				$('#row_DCLL').show();
				if (p_xa == 'Khác') {
					$('#lbl_DCLL').text("Địa chỉ liên hệ: " + p_soNha + ', ' + p_huyen + ', ' + p_tinhTp);
				} else {
					$('#lbl_DCLL').text("Địa chỉ liên hệ: " + p_soNha + ', ' + p_xa + ', ' + p_huyen + ', ' + p_tinhTp);
				}
			} else {
				swal({
					title: "Lỗi nhập dữ liệu",
					text: "Bạn chưa chọn Tỉnh/Thành phố, Quận/Huyện, Phường/Xã",
					type: "error"
				});
			}
		} catch (e) {
			swal({
				title: "Lỗi nhập dữ liệu",
				text: "Bạn chưa chọn Tỉnh/Thành phố, Quận/Huyện, Phường/Xã",
				type: "error"
			});
		}
	});
	// Check validate
	jQuery.validator.addMethod("dateFormat", function (value,
		element) {
		var check = false;
		var re = /^\d{1,2}\/\d{1,2}\/\d{4}$/;
		if (re.test(value)) {
			var adata = value.split('/');
			var dd = parseInt(adata[0], 10);
			var mm = parseInt(adata[1], 10);
			var yyyy = parseInt(adata[2], 10);
			var xdata = new Date(yyyy, mm - 1, dd);
			if ((xdata.getFullYear() === yyyy)
				&& (xdata.getMonth() === mm - 1)
				&& (xdata.getDate() === dd)) {
				check = true;
			} else {
				check = false;
			}
		} else {
			check = false;
		}
		return this.optional(element) || check;
	}, "Vui lòng nhập đúng định dạng ngày/tháng/năm");

	$("#frmRegister").validate({
		ignore: "not:hidden",
		onfocusout: false,
		onkeyup: false,
		onclick: false,
		rules: {
			fullName: "required",
			gender: "required",
			birthDate: {
				required: true,
				minlength: 10,
				dateFormat: true
			},
			birthPlace: "required",
			phone: {
				required: true,
				minlength: 8
			},
			idPassport: {
				required: true,
				minlength: 8
			},
			idPassportRetype: {
				required: true,
				minlength: 8,
				equalTo: "#p_cmndSo"
			},
			idPassportDate: {
				required: true,
				minlength: 10,
				dateFormat: true
			},
			idPassportPlace: "required",
			email: {
				required: true,
				email: true
			},
			emailRetype: {
				required: true,
				email: true,
				equalTo: "#p_email_1"
			},
			cityPlace: "required",
			districtPlace: "required",
			wardPlace: "required",
			streetPlace: "required",
			idPassportImageBack: "required",
			idPassportImageFront: "required"
		},
		messages: {
			fullName: "Nhập họ và tên",
			gender: "Chọn giới tính",
			birthDate: {
				required: "Nhập ngày sinh",
				minlength: "Sai định dạng ngày sinh (dd/mm/yyyy)",
				dateFormat: "Sai định dạng ngày sinh (dd/mm/yyyy)"
			},
			birthPlace: "Nhập nơi sinh",
			phone: {
				required: "Nhập số điện thoại",
				minlength: "Số điện thoại không hợp lệ"
			},
			idPassport: {
				required: "Nhập số CCCD/CMND/HC",
				minlength: "Số CCCD/CMND/HC không hợp lệ"
			},
			idPassportRetype: {
				required: "Nhập lại số CCCD/CMND/HC",
				minlength: "Số CCCD/CMND/HC không hợp lệ",
				equalTo: "Nhập lại số CCCD/CMND/HC"
			},
			idPassportDate: {
				required: "Nhập ngày cấp CCCD/CMND/HC",
				minlength: "Ngày cấp CCCD/CMND/HC không hợp lệ",
				dateFormat: "Ngày cấp CCCD/CMND/HC không hợp lệ"
			},
			idPassportPlace: "Nhập nơi cấp CCCD/CMND/HC",
			email: {
				required: "Nhập địa chỉ email",
				email: "Sai định dạng địa chỉ email"
			},
			emailRetype: {
				required: "Nhập lại địa chỉ email",
				email: "Sai định dạng địa chỉ email",
				equalTo: "Nhập lại địa chỉ email"
			},
			cityPlace: "Chọn Tỉnh/Thành phố liên lạc",
			districtPlace: "Chọn Quận/Huyện liên lạc",
			wardPlace: "Chọn Phường/Xã liên lạc",
			streetPlace: "Nhập địa chỉ liên lạc",
			idPassportImageBack: "Chọn hình mặt sau CCCD/CMND/HC",
			idPassportImageFront: "Chọn hình mặt trước CCCD/CMND/HC"
		}
	});

	//submit
	$("#frmRegister").submit(function (event) {
		event.preventDefault();
		if (!$("#frmRegister").valid()) {
			return;
		}
		//call ajax
		try {
			$.ajax({
				type: "post",
				url: "/register",
				data: $('form[name=frmRegister]').serialize(),
				success: function (response) {
					if (response.code == "200") {
						window.location.href = "registerSuccessful";
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