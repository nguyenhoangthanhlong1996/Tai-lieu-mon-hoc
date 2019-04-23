
//Hàm xử lý cộng
function cong() {
    var a = parseInt(document.getElementById("sothu1").value);
    var b = parseInt(document.getElementById("sothu2").value);

    if (validate(a, b)) {
        document.getElementById("ketqua").innerHTML = 'kết quả: ' + (a + b);
    }
}
//Hàm xử lý trừ
function tru() {
    var a = parseInt(document.getElementById("sothu1").value);
    var b = parseInt(document.getElementById("sothu2").value);

    if (validate(a, b)) {
        document.getElementById("ketqua").innerHTML = 'kết quả: ' + (a - b);
    }
}
//Hàm xử lý nhân
function nhan() {
    var a = parseInt(document.getElementById("sothu1").value);
    var b = parseInt(document.getElementById("sothu2").value);

    if (validate(a, b)) {
        document.getElementById("ketqua").innerHTML = 'kết quả: ' + (a * b);
    }
}
//Hàm xử lý chia
function chia() {
    var a = parseInt(document.getElementById("sothu1").value);
    var b = parseInt(document.getElementById("sothu2").value);

    if (validate(a, b)) {
        document.getElementById("ketqua").innerHTML = 'kết quả: ' + (a / b);
    }
}
//Hàm xử lý lũy thừa
function luythua() {
    var a = parseInt(document.getElementById("sothu1").value);
    var b = parseInt(document.getElementById("sothu2").value);

    if (validate(a, b)) {
        document.getElementById("ketqua").innerHTML = 'kết quả: ' + Math.pow(a, b);
    }
}


function validate(a, b) {
    document.getElementById("ketqua").innerHTML = '';
    if (isNaN(a) || isNaN(b)) {
        alert("Dữ liệu nhập vào phải thuộc kiểu số");
        return false;
    } else
        return true;
}