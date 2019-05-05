//function
function getEnrollmentById(enrollmentId) {
	try {
		$.ajax({
			type: "get",
			url: "/api/getEnrollment/" + enrollmentId,
			data: "",
			success: function (response) {
				if (response.code == "200") {
					var obj = response.data;
					$('#enrollmentName').val(obj.courseEnrollmentName);
                    $('#enrollmentNbr').val(obj.courseEnrollmentNbr);
                    $('#enrollmentBegin').val($.formatDateTime('dd/mm/yy hh:ii', new Date(obj.courseEnrollmentBegin)));
                    $('#enrollmentEnd').val($.formatDateTime('dd/mm/yy hh:ii', new Date(obj.courseEnrollmentEnd)));
                    $('#enrollmentDesc').val(obj.courseEnrollmentDesc);
					if (obj.courseEnrollmentEnable) {
						$('#enrollmentEnable').prop('checked', true);
					} else {
						$('#enrollmentEnable').prop('checked', false);
					}
                    $('#enrollmentId').val(obj.courseEnrollmentId);
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
					text: "Không tải được thông tin đợt đăng ký"
				});
			}
		});
	} catch (error) {
		console.log(error.message);
	}
}
function deleteEnrollmentById(enrollmentId) {
	try {
		$.ajax({
			type: "post",
			url: "/api/deleteEnrollment",
			data: "id=" + enrollmentId,
			success: function (response) {
				if (response.code == "200") {
					Swal.fire(
						'kết quả hủy đợt đăng ký',
						'Đợt đăng ký bạn chọn đã được hủy',
						'success'
					);
					//remove
					var myTable = $('#lstEnrollments').DataTable();
					var _tr;
					$("#lstEnrollments tbody tr").each(function () {
						var _id = $(this).data('id');
						if (_id == enrollmentId) {
							_tr = $(this);
							return false;
						}
					});
					myTable.row(_tr).remove().draw(false);
				} else {
					Swal.fire({
						type: 'error',
						title: 'Lỗi hủy bỏ đợt đăng ký',
						text: response.msg
					});
				}
			},
			error: function () {
				Swal.fire({
					type: 'error',
					title: 'Lỗi hủy bỏ đợt đăng ký',
					text: 'Không thể thực hiện hủy bỏ đợt đăng ký đã chọn'
				});
			}
		});
	} catch (err) {
		console.log(err.message);
	}
}
function clearDataForm() {
    $('#frmEnrollments input[type=text]').val('');
    $('#enrollmentId').val(0);
    $('#enrollmentDesc').val('');
}

$(document).ready(function () {
    //Validate
    jQuery.validator.addMethod("datetimeFormat", function (value,
        element) {
        var check = false;
        var arr = value.split(' ');
        if (arr.length != 2) {
            check = false;
            return this.optional(element) || check;
        }
        var date = arr[0];
        var time = arr[1];
        //regular expression to match required date format
        var re = /^\d{1,2}\/\d{1,2}\/\d{4}$/;
        if (re.test(date)) {
            var adata = date.split('/');
            var dd = parseInt(adata[0], 10);
            var mm = parseInt(adata[1], 10);
            var yyyy = parseInt(adata[2], 10);
            var xdata = new Date(yyyy, mm - 1, dd);
            if ((xdata.getFullYear() === yyyy)
                && (xdata.getMonth() === mm - 1)
                && (xdata.getDate() === dd)) {
                // regular expression to match required time format
                re = /^\d{2}:\d{2}$/;
                if (re.test(time)) {
                    check = true;
                } else {
                    check = false;
                }
            } else {
                check = false;
            }
        } else {
            check = false;
        }
        return this.optional(element) || check;
    }, "Vui lòng nhập đúng định dạng ngày/tháng/năm giờ:phút");
    $("#frmEnrollments").validate({
        ignore: "not:hidden",
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        rules: {
            enrollmentName: "required",
            enrollmentNbr: "required",
            enrollmentBegin: {
                required: true,
                datetimeFormat: true
            },
            enrollmentEnd: {
                required: true,
                datetimeFormat: true
            }
        },
        messages: {
            enrollmentName: "Nhập tên đợt đăng ký",
            enrollmentNbr: "Nhập mã đợt đăng ký",
            enrollmentBegin: {
                required: "Nhập thời gian bắt đầu",
                datetimeFormat: "Thời gian bắt đầu không đúng định dạng"
            },
            enrollmentEnd: {
                required: "Nhập thời gian kết thúc",
                datetimeFormat: "Thời gian kết thúc không đúng định dạng"
            }
        }
    });
    //end validate
    //Load table
	$('#lstEnrollments').DataTable({
		"columnDefs": [{
			"targets": 'no-sort',
			"orderable": false,
		}],
		"aaSorting": [[2, "asc"]]
	});
    //Events
	$(document).on('click', '#btn_edit', function () {
		var enrollmentId = $(this).attr("title");
		if (enrollmentId) {
			getEnrollmentById(enrollmentId);
		}
    });
    $(document).on('click', '#btn_delete', function () {
		var enrollmentId = $(this).attr("title");
		if (enrollmentId) {
			Swal.fire({
				title: 'Bạn có chắc muốn hủy đợt đăng ký này?',
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
					deleteEnrollmentById(enrollmentId);
				}
			})
		}
	});
    $('#bnt_Reset').click(function () {
		clearDataForm();
	});
    //submit
	$("#frmEnrollments").submit(function (event) {
		event.preventDefault();
		if (!$("#frmEnrollments").valid()) {
			return;
        }
        //call ajax
		try {
			$.ajax({
				type: "post",
				url: "/enrollments",
				data: $('form[name=frmEnrollments]').serialize(),
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
							var enrollmentId = responseData.courseEnrollmentId;
							var table = $('#lstEnrollments').DataTable();
							var _tr;
							var _data;
							//Modify
							if (response.msg == "update") {
								//Find td
								$("#lstEnrollments tbody tr").each(function () {
									var _id = $(this).data('id');
									if (_id == enrollmentId) {
										_tr = $(this);
										return false;
									}
								});
								//
								_data = table.row(_tr).data();
								if (responseData.courseEnrollmentEnable) {
									_data[1] = '<input type="checkbox" disabled="disabled" checked="checked">';
								} else {
									_data[1] = '<input type="checkbox" disabled="disabled">';
								}
								_data[2] = responseData.courseEnrollmentNbr;
								_data[3] = responseData.courseEnrollmentName;
								_data[4] = responseData.courseEnrollmentBegin;
								_data[5] = responseData.courseEnrollmentEnd;
								table.row(_tr).data(_data).draw();
							} else {
								//Add new
								_data = [];
								if (responseData.courseEnrollmentEnable) {
									_data[1] = '<input type="checkbox" disabled="disabled" checked="checked">';
								} else {
									_data[1] = '<input type="checkbox" disabled="disabled">';
								}
								_data[2] = responseData.courseEnrollmentNbr;
								_data[3] = responseData.courseEnrollmentName;
								_data[4] = responseData.courseEnrollmentBegin;
								_data[5] = responseData.courseEnrollmentEnd;
								//Add group btn
								var _divbtnGroup = '<div class="btn-group">';
								_divbtnGroup += '<button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown">';
								_divbtnGroup += '<span class="caret"></span> <span class="sr-only">Tùy chọn</span>';
								_divbtnGroup += '</button>';
								_divbtnGroup += '<ul class="dropdown-menu" role="menu">';
								_divbtnGroup += '<li><a title="' + enrollmentId + '" id="btn_edit" href="#frmEnrollments">Chỉnh sửa</a></li>';
								_divbtnGroup += '<li class="divider"></li>';
								_divbtnGroup += '<li><a title="' + enrollmentId + '" id="btn_delete" href="#lstEnrollments">Hủy bỏ</a></li>';
								_divbtnGroup += '</ul>';
								_divbtnGroup += '</div>';
								_data[0] = _divbtnGroup;
								var rowNode = table.row.add(_data).draw(false).node();
								$(rowNode).attr('data-id', enrollmentId);
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