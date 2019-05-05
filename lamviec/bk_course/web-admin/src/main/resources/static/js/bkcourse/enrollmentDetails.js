//function
function getEnrollmentDetailById(enrollmentDetailId) {
    try {
        $.ajax({
            type: "get",
            url: "/api/getEnrollmentDetail/" + enrollmentDetailId,
            data: "",
            success: function (response) {
                if (response.code == "200") {
                    var obj = response.data;
                    $('#enrollmentId').val(obj.enrollmentId);
                    $('#courseId').val(obj.courseId);
                    $('#slMax').val(obj.slMax);
                    $('#enrollmentDetailId').val(obj.enrollmentDetailId);
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
                    text: "Không tải được thông tin chi tiết đợt đăng ký"
                });
            }
        });
    } catch (error) {
        console.log(error.message);
    }
}
function deleteEnrollmentDetailById(enrollmentDetailId) {
    try {
        $.ajax({
            type: "post",
            url: "/api/deleteEnrollmentDetail",
            data: "id=" + enrollmentDetailId,
            success: function (response) {
                if (response.code == "200") {
                    Swal.fire(
                        'kết quả hủy chi tiết đợt đăng ký',
                        'Chi tiết đợt đăng ký bạn chọn đã được hủy',
                        'success'
                    );
                    //remove
                    var myTable = $('#lstEnrollmentDetails').DataTable();
                    var _tr;
                    $("#lstEnrollmentDetails tbody tr").each(function () {
                        var _id = $(this).data('id');
                        if (_id == enrollmentDetailId) {
                            _tr = $(this);
                            return false;
                        }
                    });
                    myTable.row(_tr).remove().draw(false);
                } else {
                    Swal.fire({
                        type: 'error',
                        title: 'Lỗi hủy bỏ chi tiết đợt đăng ký',
                        text: response.msg
                    });
                }
            },
            error: function () {
                Swal.fire({
                    type: 'error',
                    title: 'Lỗi hủy bỏ chi tiết đợt đăng ký',
                    text: 'Không thể thực hiện hủy bỏ chi tiết đợt đăng ký đã chọn'
                });
            }
        });
    } catch (err) {
        console.log(err.message);
    }
}
function clearDataForm() {
    $('#frmEnrollmentDetails input[type=text]').val('');
    $('#enrollmentId').val('');
    $('#courseId').val('');
    $('#enrollmentDetailId').val(0);
}

$(document).ready(function () {
    //Validate
    $("#frmEnrollmentDetails").validate({
        ignore: "not:hidden",
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        rules: {
            enrollmentId: "required",
            courseId: "required",
            slMax: {
                required: true,
                number: true
            }
        },
        messages: {
            enrollmentId: "Chọn đợt đăng ký",
            courseId: "chọn khóa học",
            slMax: {
                required: "Nhập sĩ số tối đa",
                number: "Sĩ số phải là kiểu số"
            }
        }
    });
    //end validate
    //Load table
    $('#lstEnrollmentDetails').DataTable({
        "columnDefs": [{
            "targets": 'no-sort',
            "orderable": false,
        }],
        "aaSorting": [[2, "asc"]]
    });
    //Events
    $(document).on('click', '#btn_edit', function () {
        var enrollmentDetailId = $(this).attr("title");
        if (enrollmentDetailId) {
            getEnrollmentDetailById(enrollmentDetailId);
        }
    });
    $(document).on('click', '#btn_delete', function () {
        var enrollmentDetailId = $(this).attr("title");
        if (enrollmentDetailId) {
            Swal.fire({
                title: 'Bạn có chắc muốn hủy chi tiết đợt đăng ký này?',
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
                    deleteEnrollmentDetailById(enrollmentDetailId);
                }
            })
        }
    });
    $('#bnt_Reset').click(function () {
        clearDataForm();
    });
    //submit
    $("#frmEnrollmentDetails").submit(function (event) {
        event.preventDefault();
        if (!$("#frmEnrollmentDetails").valid()) {
            return;
        }
        //call ajax
        try {
            $.ajax({
                type: "post",
                url: "/enrollmentDetails",
                data: $('form[name=frmEnrollmentDetails]').serialize(),
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
                            var enrollmentDetailId = responseData.enrollmentDetailId;
                            var table = $('#lstEnrollmentDetails').DataTable();
                            var _tr;
                            var _data;
                            //Modify
                            if (response.msg == "update") {
                                //Find td
                                $("#lstEnrollmentDetails tbody tr").each(function () {
                                    var _id = $(this).data('id');
                                    if (_id == enrollmentDetailId) {
                                        _tr = $(this);
                                        return false;
                                    }
                                });
                                //
                                _data = table.row(_tr).data();
                                _data[1] = responseData.slMax;
                                _data[2] = $('#enrollmentId option[value=' + responseData.enrollmentId + ']').text();
                                _data[3] = $('#courseId option[value=' + responseData.courseId + ']').text();
                                table.row(_tr).data(_data).draw();
                            } else {
                                //Add new
                                _data = [];
                                _data[1] = responseData.slMax;
                                _data[2] = $('#enrollmentId option[value=' + responseData.enrollmentId + ']').text();
                                _data[3] = $('#courseId option[value=' + responseData.courseId + ']').text();
                                //Add group btn
                                var _divbtnGroup = '<div class="btn-group">';
                                _divbtnGroup += '<button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown">';
                                _divbtnGroup += '<span class="caret"></span> <span class="sr-only">Tùy chọn</span>';
                                _divbtnGroup += '</button>';
                                _divbtnGroup += '<ul class="dropdown-menu" role="menu">';
                                _divbtnGroup += '<li><a title="' + enrollmentDetailId + '" id="btn_edit" href="#frmEnrollmentDetails">Chỉnh sửa</a></li>';
                                _divbtnGroup += '<li class="divider"></li>';
                                _divbtnGroup += '<li><a title="' + enrollmentDetailId + '" id="btn_delete" href="#lstEnrollmentDetails">Hủy bỏ</a></li>';
                                _divbtnGroup += '</ul>';
                                _divbtnGroup += '</div>';
                                _data[0] = _divbtnGroup;
                                var rowNode = table.row.add(_data).draw(false).node();
                                $(rowNode).attr('data-id', enrollmentDetailId);
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