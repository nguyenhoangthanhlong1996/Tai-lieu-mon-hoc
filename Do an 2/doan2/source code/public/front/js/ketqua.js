$(document).ready(function () {
    //Chỉnh lại hiển thị nội dung câu hỏi
    $('.item-result p[class="content"]').each(function() {
        var oldContent = $(this).html();
        $(this).html(oldContent.replace(/(_{1,})/gm,'_____'));
    });
});