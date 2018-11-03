$(document).ready(function() {

    //Sự kiện khi form được submit
    $("form").submit(function (e) {
        if (ThongTinHopLe()) {
            alert("Đăng ký thành công");
        } else {
            e.preventDefault();
        }
    });
});

//Hàm kiểm tra thông tin người dùng nhậprom
function ThongTinHopLe() {
    //Lấy giá trị trong thẻ input họ tên và kiểm tra
    var hoten = $('#hoten').val();
    if (hoten.length<2 || hoten.length>30) {
        alert('Họ tên phải có độ dài từ 2-30 kí tự');
        return false;
    }
    //Lấy giá trị trong thẻ input tên đăng nhập và kiểm tra
    var tendangnhap = $('#tendangnhap').val();
    if (tendangnhap.length<2 || tendangnhap.length>20) {
        alert('Tên đăng nhập phải có độ dài từ 2-20 kí tự');
        return false;
    }
    //Lấy giá trị trong thẻ input mật khẩu và kiểm tra
    var matkhau = $('#matkhau').val();
    if (matkhau.length<6 || matkhau.length>20) {
        alert('Mật khẩu phải có độ dài từ 6-20 kí tự');
        return false;
    }
    //Lấy giá trị trong thẻ input email và kiểm tra
    var email = $('#email').val();
    if (!EmailHopLe(email)) {
        alert('Email không hợp lệ');
        return false;
    }
    //Lấy giá trị trong thẻ input thông tin sơ lược và kiểm tra
    var thongtinsoluoc = $('#thongtinsoluoc').val();
    if (thongtinsoluoc.length>1000) {
        alert('Thông tin sơ lược không được quá 1000 kí tự');
        return false;
    }
    //Lấy giá trị trong thẻ input thông tin sơ lược và kiểm tra
    var thongtinsoluoc = $('#thongtinsoluoc').val();
    if (thongtinsoluoc.length>1000) {
        alert('Thông tin sơ lược không được quá 1000 kí tự');
        return false;
    }
    //Lấy giá trị được chọn trong thẻ input radio đủ tuổi và kiểm tra
    if (($("input[name='dutuoi']:checked").val()) == undefined) {
        alert('Bạn trên 18 tuổi ? Phải chọn đúng hoặc sai');
        return false;
    }
    //Lấy giá trị được chọn trong thẻ input checkbox đồng ý điều khoản và kiểm tra
    if (($('#dongydieukhoan').prop("checked")) == false) {
        alert('Bạn phải chọn vào ô đồng ý điều khoản');
        return false;
    }
    return true;
}

//Hàm kiểm tra 1 email hợp lệ
function EmailHopLe(email) {
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if (!filter.test(email)) {
         return false;
    }
    else{
         return true;
    }
}
