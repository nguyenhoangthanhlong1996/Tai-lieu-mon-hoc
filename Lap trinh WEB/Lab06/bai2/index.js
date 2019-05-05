//Chèn dữ liệu cho các select ngày tháng năm sinh
var day = document.getElementById("day");
var month = document.getElementById("month");
var year = document.getElementById("year");
//Chèn vào select ngày
for (i = 1; i <= 31; i++) {
    var option = document.createElement('option');
    option.text = i;
    option.value = i;
    day.appendChild(option);
}
//Chèn vào select tháng
for (i = 1; i <= 12; i++) {
    var option = document.createElement('option');
    option.text = i;
    option.value = i;
    month.appendChild(option);
}
//Chèn vào select năm
var now = new Date().getUTCFullYear();
for (i = now - 110; i <= now; i++) {
    var option = document.createElement('option');
    option.text = i;
    option.value = i;
    year.appendChild(option);
}

//Hàm xử lý khi nhấn submit
function submitForm() {
    var errors = '';
    var firstname = document.getElementById('firstname').value;
    var lastname = document.getElementById('lastname').value;
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;
    // var about = document.getElementById('about').value;

    //Kiểm tra firstname
    if (firstname.length < 2 || firstname.length > 30)
        errors += 'First name phải từ 2 - 30 kí tự\n';

    //Kiểm tra lastname
    if (lastname.length < 2 || lastname.length > 30)
        errors += 'Last name phải từ 2 - 30 kí tự\n';

    //Kiểm tra email
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (email == '' || !re.test(email))
        errors += 'Email không hợp lệ\n'

    //Kiểm tra password
    if (password.length < 2 || password.length > 30)
        errors += 'Password phải từ 2 - 30 kí tự\n';

    //Kiểm tra birthday
    if (!isValidDate(year.value + '-' + month.value + '-' + day.value))
        errors += 'Birthday không hợp lệ\n';

    //Kiểm tra about
    if (password.length > 10000)
        errors += 'About tối đa 10000 kí tự\n';


    //Thông báo kết quả
    if (errors != '') {
        alert(errors);
    } else {
        alert('Complete !');
    }
}

//Hàm kiểm tra ngày sinh hợp lệ yyyy-mm-dd
function isValidDate(dateString) {
    // First check for the pattern
    var regex_date = /^\d{4}\-\d{1,2}\-\d{1,2}$/;

    if (!regex_date.test(dateString)) {
        return false;
    }

    // Parse the date parts to integers
    var parts = dateString.split("-");
    var day = parseInt(parts[2], 10);
    var month = parseInt(parts[1], 10);
    var year = parseInt(parts[0], 10);

    // Check the ranges of month and year
    if (year < 1000 || year > 3000 || month == 0 || month > 12) {
        return false;
    }

    var monthLength = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

    // Adjust for leap years
    if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) {
        monthLength[1] = 29;
    }

    // Check the range of the day
    return day > 0 && day <= monthLength[month - 1];
}
