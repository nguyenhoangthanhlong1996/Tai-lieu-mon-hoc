//function
function clearDataForm() {
    $('#firstName').val('');
    $('#lastName').val('');
    $('#userName').val('');
    $('#email').val('');
    $('#passwordLogin').val('');
    $('#passwordLogin').prop('disabled', false);
    $('#role').val('');
    $('#userId').val(0);
}
function getUserById(userId) {
    try {
        $.ajax({
            type: "get",
            url: "/api/getUser/" + userId,
            data: "",
            success: function (response) {
                if (response.code == "200") {
                    var obj = response.data;
                    $('#firstName').val(obj.firstName);
                    $('#lastName').val(obj.lastName);
                    $('#userName').val(obj.userName);
                    $('#email').val(obj.email);
                    $('#passwordLogin').val('');
                    $('#passwordLogin').prop('disabled', true);
                    $('#role').val(obj.roles[0].roleId);
                    if (obj.active) {
                        $('#active').prop('checked', true);
                    } else {
                        $('#active').prop('checked', false);
                    }
                    $('#userId').val(obj.userId);
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
                    text: "Không tải được thông tin người dùng"
                });
            }
        });
    } catch (error) {
        console.log(error.message);
    }
}
function deleteUserById(userId) {
    try {
        $.ajax({
            type: "post",
            url: "/api/deleteUser",
            data: "id=" + userId,
            success: function (response) {
                if (response.code == "200") {
                    Swal.fire(
                        'kết quả hủy thông tin người dùng',
                        'Thông tin người dùng bạn chọn đã được hủy',
                        'success'
                    );
                    //remove
                    var myTable = $('#lstUsers').DataTable();
                    var _tr;
                    $("#lstUsers tbody tr").each(function () {
                        var _id = $(this).data('id');
                        if (_id == userId) {
                            _tr = $(this);
                            return false;
                        }
                    });
                    myTable.row(_tr).remove().draw(false);
                } else {
                    Swal.fire({
                        type: 'error',
                        title: 'Lỗi hủy bỏ thông tin người dùng',
                        text: response.msg
                    });
                }
            },
            error: function () {
                Swal.fire({
                    type: 'error',
                    title: 'Lỗi hủy bỏ thông tin người dùng',
                    text: 'Không thể thực hiện hủy bỏ thông tin người dùng đã chọn'
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
    //Validate
    $("#frmUsers").validate({
        ignore: "not:hidden",
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        rules: {
            firstName: "required",
            lastName: "required",
            userName: "required",
            passwordLogin: "required",
            email: {
                required: true,
                email: true
            },
            role: "required"
        },
        messages: {
            firstName: "Nhập họ và tên lót",
            lastName: "Nhập tên",
            userName: "Nhập tên đăng nhập",
            passwordLogin: "Nhập mật khẩu đăng nhập",
            email: {
                required: "Nhập địa chỉ email",
                email: "Địa chỉ email sai định dạng"
            },
            role: "Chọn nhóm người dùng"
        }
    });
    //end validate
    //Load table
    $('#lstUsers').DataTable({
        "columnDefs": [{
            "targets": 'no-sort',
            "orderable": false,
        }],
        "aaSorting": [[1, "asc"]]
    });
    //Events
    $(document).on('click', '#btn_edit', function () {
        var userId = $(this).attr("title");
        if (userId) {
            getUserById(userId);
        }
    });
    $(document).on('click', '#btn_delete', function () {
        var userId = $(this).attr("title");
        if (userId) {
            Swal.fire({
                title: 'Bạn có chắc muốn hủy thông tin người dùng này?',
                text: "Bạn sẽ không thể khôi phục lại!",
                type: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Đồng ý',
                cancelButtonText: 'Bỏ qua',
            }).then((result) => {
                if (result.value) {
                    deleteUserById(userId);
                }
            })
        }
    });
    $('#bnt_Reset').click(function () {
        clearDataForm();
    });
    //submit
    $("#frmUsers").submit(function (event) {
        event.preventDefault();
        if (!$("#frmUsers").valid()) {
            return;
        }
        //call ajax
        try {
            $.ajax({
                type: "post",
                url: "/users",
                data: $('form[name=frmUsers]').serialize(),
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
                            var userId = responseData.userId;
                            var table = $('#lstUsers').DataTable();
                            var _tr;
                            var _data;
                            //Modify
                            if (response.msg == "update") {
                                //Find td
                                $("#lstUsers tbody tr").each(function () {
                                    var _id = $(this).data('id');
                                    if (_id == userId) {
                                        _tr = $(this);
                                        return false;
                                    }
                                });
                                //
                                _data = table.row(_tr).data();
                                _data[1] = responseData.userName;
                                _data[2] = responseData.email;
                                _data[3] = responseData.firstName;
                                _data[4] = responseData.lastName;
                                table.row(_tr).data(_data).draw();
                            } else {
                                //Add new
                                _data = [];
                                _data[1] = responseData.userName;
                                _data[2] = responseData.email;
                                _data[3] = responseData.firstName;
                                _data[4] = responseData.lastName;
                                //Add group btn
                                var _divbtnGroup = '<div class="btn-group">';
                                _divbtnGroup += '<button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown">';
                                _divbtnGroup += '<span class="caret"></span> <span class="sr-only">Tùy chọn</span>';
                                _divbtnGroup += '</button>';
                                _divbtnGroup += '<ul class="dropdown-menu" role="menu">';
                                _divbtnGroup += '<li><a title="' + userId + '" id="btn_edit" href="#frmCourses">Chỉnh sửa</a></li>';
                                _divbtnGroup += '<li class="divider"></li>';
                                _divbtnGroup += '<li><a title="' + userId + '" id="btn_delete" href="#lstCourses">Hủy bỏ</a></li>';
                                _divbtnGroup += '</ul>';
                                _divbtnGroup += '</div>';
                                _data[0] = _divbtnGroup;
                                var rowNode = table.row.add(_data).draw(false).node();
                                $(rowNode).attr('data-id', userId);
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