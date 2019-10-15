$(document).ready(function () {
   
    $('form').on('click', 'a.delete', function() {
        let form = $(this).parent()[0];
        $('.dialog-delete').css('display','block');
        $('button.btn-accept').click(function() {
            console.log(form);
            form.submit();
        });
        $('button.btn-cancel').click(function() {
            $('.dialog-delete').css('display','none');
        });
    });
});

$('form').on('click', 'a.delete', function() {
    let form = $(this).parent()[0];
    $('.dialog-delete').css('display','block');
    $('button.btn-accept').click(function() {
        console.log(form);
        form.submit();
    });
    $('button.btn-cancel').click(function() {
        $('.dialog-delete').css('display','none');
    });
});