//Định nghĩa các biến toàn cục
var odado = 0;
var dangchoi = 0;
var data_tbl;
var stat_tbl;

//hàm khởi tạo mãng ₫ộng
function MakeArray(size) {
    this.length = size;
    return this;
}

//hàm khởi tạo trò chơi dò mìn
function wm_init() {
    var i, j;
    odado = 0;
    dangchoi = 1;
    //hiển thị bàn mìn trống
    for (i = 0; i < 64; i++) {
        document.images[i].src = "img/wm_nul.gif";
        document.images[i].setAttribute("data-index", i + 9);
    }
    //hiển thị chuỗi cảnh báo và ẩn button “Chơi tiếp”
    document.all.Mesg.innerText = "Hãy dò từng ô mìn";
    document.all.IDSTART.style.display = "none";
    //tạo các bảng dữ liệu và thiết lập giá trị ₫ầu.
    data_tbl = new MakeArray(64);
    stat_tbl = new MakeArray(64);
    for (i = 0; i < 64; i++) data_tbl[i] = stat_tbl[i] = 0;
    //xếp ngẫu nhiên 10 trái mìn
    i = 0;
    while (i < 10) {
        j = Math.round(Math.random() * 62);
        if (data_tbl[j] == 0) {
            data_tbl[j] = 1;
            i++;
        }
    }
}

//hàm tính số mìn xung quanh vị trí (h,c)
function winecnt(h, c) {
    var cnt = 0;
    if (h - 1 >= 0 && c - 1 >= 0 && data_tbl[(h - 1) * 8 + (c - 1)]) cnt++;
    if (h - 1 >= 0 && data_tbl[(h - 1) * 8 + c]) cnt++;
    if (h - 1 >= 0 && c + 1 < 8 && data_tbl[(h - 1) * 8 + (c + 1)]) cnt++;
    if (c - 1 >= 0 && data_tbl[h * 8 + (c - 1)]) cnt++;
    if (c + 1 < 8 && data_tbl[h * 8 + (c + 1)]) cnt++;
    if (h + 1 < 8 && c - 1 >= 0 && data_tbl[(h + 1) * 8 + (c - 1)]) cnt++;
    if (h + 1 < 8 && data_tbl[(h + 1) * 8 + c]) cnt++;
    if (h + 1 < 8 && c + 1 < 8 && data_tbl[(h + 1) * 8 + (c + 1)]) cnt++;
    return cnt;
}

//hàm click dùm tự ₫ộng các ô bao quanh vị trí (h,c)
function doquanh(h, c) {
    if (h - 1 >= 0 && c - 1 >= 0 && stat_tbl[(h - 1) * 8 + (c - 1)] == 0) domin(h - 1, c - 1);
    if (h - 1 >= 0 && stat_tbl[(h - 1) * 8 + c] == 0) domin(h - 1, c);
    if (h - 1 >= 0 && c + 1 < 8 && stat_tbl[(h - 1) * 8 + (c + 1)] == 0) domin(h - 1, c + 1);
    if (c - 1 >= 0 && stat_tbl[h * 8 + (c - 1)] == 0) domin(h, c - 1);
    if (c + 1 < 8 && stat_tbl[h * 8 + (c + 1)] == 0) domin(h, c + 1);
    if (h + 1 < 8 && c - 1 >= 0 && stat_tbl[(h + 1) * 8 + (c - 1)] == 0) domin(h + 1, c - 1);
    if (h + 1 < 8 && stat_tbl[(h + 1) * 8 + c] == 0) domin(h + 1, c);
    if (h + 1 < 8 && c + 1 < 8 && stat_tbl[(h + 1) * 8 + (c + 1)] == 0) domin(h + 1, c + 1);
}

//hàm xử lý việc click vào ô (h,c)
function domin(h, c) {
    var i, cnt;
    i = h * 8 + c;
    console.log(i);
    if (stat_tbl[i]) return 0; //₫ã ₫ạp rồi ô này
    if (data_tbl[i] == 1) { //₫ạp trúng mìn
        for (h = 0; h < 64; h++) {
            if (data_tbl[h] == 1) document.images[h].src = "img/wm_mini.gif";
        }
        document.images[i].src = "img/wm_mina.gif";
        document.all.Mesg.innerText = "Bạn ₫ã chết";
        document.all.IDSTART.style.display = "";
        dangchoi = 0;
    } else { //dò ô không có mìn
        cnt = winecnt(h, c);
        stat_tbl[i] = 1;
        if (cnt == 0) {
            document.images[i].src = "img/wm_nul1.gif";
            doquanh(h, c);
        }
        if (cnt == 1) document.images[i].src = "img/wm_1.gif";
        if (cnt == 2) document.images[i].src = "img/wm_2.gif";
        if (cnt == 3) document.images[i].src = "img/wm_3.gif";
        if (cnt == 4) document.images[i].src = "img/wm_4.gif";
        if (cnt == 5) document.images[i].src = "img/wm_5.gif";
        if (cnt == 6) document.images[i].src = "img/wm_6.gif";
        if (cnt == 7) document.images[i].src = "img/wm_7.gif";
        if (cnt == 8) document.images[i].src = "img/wm_8.gif";
        odado++;
    }
    if (odado == 54) {
        document.all.Mesg.innerText = "Bạn ₫ã thắng";
        document.all.IDSTART.style.display = "";
        dangchoi = 0;
    }
}

//hàm xử lý ấn chuột trên trang Web
function document_onmousedown() {
    var i, h;
    if (dangchoi == 0) return 0;
    //xác ₫ịnh vị trí luận lý của phần tử ₫ược user ấn chuột
    //9 là vị trí cell ₫ầu tiên trong bàn mìn
    i = window.event.srcElement.getAttribute('data-index');
    console.log(i);
    if (i < 0) return 0;
    //xác ₫ịnh tọa ₫ộ h,c của cell mìn ₫ược user ấn chuột
    c = i % 9;
    h = (i - c) / 9;
    if (h >= 8 || c == 8) return 0;
    domin(h, c); //xử lý việc ấn cell h,c
}

//hàm xử lý click chuột trên button Start
function btnStart_onclick() {
    wm_init();
}
