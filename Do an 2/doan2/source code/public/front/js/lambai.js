$(document).ready(function () {
    //Lấy thời gian làm bài
    let time = $('#time').val();
    let interval = setInterval(function() {
        if (time == 0) {
            clearInterval(interval);
            $('#question').trigger('submit');
        }
        $('#countdowntimer').html(' '+secondsTimeSpanToHMS(time--));
    }, 1000);
    //Tính số lượng câu hỏi
    let countQuestion = $('.item-question').length;
    let currentQuestion = 1;
    //Mảng kiểm soát các câu hỏi đã chọn đáp án
    let arrChecked = new Array();
    for (i = 0; i < countQuestion; i++) {
        arrChecked.push(false);
    }
    //Chỉnh lại hiển thị nội dung câu hỏi
    $('.item-question p[class="content"]').each(function() {
        var oldContent = $(this).html();
        $(this).html(oldContent.replace(/(_{1,})/gm,'_____'));
    });
    //Tạo thanh điều hướng câu hỏi
    $('#navigate-question').append('<li class="page-item"><a class="page-link" href="#">Câu trước</a></li>')
    for (i = 1; i <= countQuestion; i++) {
        var questionId = $('.item-question').eq(i-1).attr('id');
        $('#navigate-question').append(`<li class="page-item" questionid=${questionId} index=${i}><a class="page-link" href="#">${i}</a></li>`);
    }
    $('#navigate-question').append('<li class="page-item disabled"><a class="page-link" href="#">Câu tiếp theo</a></li>')
    //Gọi kích hoạt câu đầu tiên
    questionChange(currentQuestion);

    //Bắt sự kiện điều hướng câu hỏi
    $('#navigate-question li.page-item').on('click', function () {
        questionChange($(this).attr('index'));
    });
    //Bắt sự kiện câu hỏi trước
    $('#navigate-question li.page-item:first').on('click', function () {
        questionChange(currentQuestion - 1);
    });
    //Bắt sự kiện câu hỏi tiếp theo
    $('#navigate-question li.page-item:last').on('click', function () {
        questionChange(currentQuestion + 1);
    });
    //Sự kiện có đáp án được chọn
    $('.item-question input:radio').change(function () {
        if ($(this).val() != undefined) {
            arrChecked[$(this).parents('.item-question').attr('index') - 1] = true;
        }
    });
    //Sự kiện submit form 
    $('#question').submit(function (event) {
        let errors = '';
        arrChecked.forEach((isChecked, index) => {
            if(isChecked == false) {
                errors += `Câu ${index + 1}, `;
            }   
        });
        if (errors != '' && time > 0) {
            event.preventDefault()
            alert(`Còn câu hỏi chưa đánh đáp án: ${errors}`);
        }
    });


    //Hàm xử lý khi câu hỏi khác được chọn
    function questionChange(index) {
        if (index != undefined && index >= 1 && index <= countQuestion) {
            currentQuestion = parseInt(index);
            //Xóa active các nút điều hướng câu hỏi
            $('#navigate-question li.page-item').removeClass('active');
            //Gán active cho câu hỏi tại index
            $(`#navigate-question li.page-item[index=${index}]`).addClass('active');
            //Ẩn các tất cả câu hỏi
            $('.item-question').css({
                'display': 'none'
            });
            //Hiện câu hỏi thứ index
            $(`.item-question:eq(${index - 1})`).css({
                'display': 'block'
            });
            //Kiểm tra để thiết lập lại điều hướng
            $('#navigate-question li.page-item:first').removeClass('disabled');
            $('#navigate-question li.page-item:last').removeClass('disabled');
            if (index == 1) {
                $('#navigate-question li.page-item:first').addClass('disabled');
            }
            if (index == countQuestion) {
                $('#navigate-question li.page-item:last').addClass('disabled');
            }
            //Đổi màu nền điều hướng các câu hỏi đã chọn đáp án
            $('.item-question').each(function() {
                var questionid = $(this).attr('id');
                if ($(`input:radio[name='${questionid}']:checked`).val()) {
                    var li = $(`#navigate-question li.page-item[questionid=${questionid}]`);
                    if (li && li.attr('index') != currentQuestion) {
                        li.addClass('selected');
                    }
                }
            });
        }
    }
});

function secondsTimeSpanToHMS(s) {
    var h = Math.floor(s/3600); //Get whole hours
    s -= h*3600;
    var m = Math.floor(s/60); //Get remaining minutes
    s -= m*60;
    return h+":"+(m < 10 ? '0'+m : m)+":"+(s < 10 ? '0'+s : s); //zero padding on minutes and seconds
}