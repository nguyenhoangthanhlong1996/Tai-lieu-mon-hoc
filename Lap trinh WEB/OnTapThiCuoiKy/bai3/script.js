function check() {
    var name = document.getElementById("name").value;
    var day = document.getElementById("day").value;
    var month = document.getElementById("month").value;
    var year = document.getElementById("year").value;
    if (
        name.length < 2 
        || name.length > 20
        || day < 1
        || day > 31
        || month < 1
        || month > 12
        || year < 1900
        || year > 2016
        ) {
        alert("Lỗi dữ liệu");
        return;
    }
    alert(name+" - "+day+"/"+month+"/"+year);
}
