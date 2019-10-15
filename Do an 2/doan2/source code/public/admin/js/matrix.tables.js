
$(document).ready(function(){
	
	$('.data-table').dataTable({
		"bJQueryUI": true,
		"sPaginationType": "full_numbers",
		"sDom": '<""l>t<"F"fp>',
		"oLanguage": {
			"sLengthMenu": "Hiển thị _MENU_ dòng",
			"sEmptyTable": "Không có dữ liệu",
			"sZeroRecords": "Không có dữ liệu cần tìm trong bảng",
			"sSearch": "Tìm kiếm:",
			"oPaginate": {
				"sFirst": "Trang đầu", // This is the link to the first page
				"sPrevious": "Trang trước", // This is the link to the previous page
				"sNext": "Trang kế", // This is the link to the next page
				"sLast": "Trang cuối" // This is the link to the last page
			}
		}
	});
	
	$('input[type=checkbox],input[type=radio],input[type=file]').uniform();
	
	$('select').select2();
	
	$("span.icon input:checkbox, th input:checkbox").click(function() {
		var checkedStatus = this.checked;
		var checkbox = $(this).parents('.widget-box').find('tr td:first-child input:checkbox');		
		checkbox.each(function() {
			this.checked = checkedStatus;
			if (checkedStatus == this.checked) {
				$(this).closest('.checker > span').removeClass('checked');
			}
			if (this.checked) {
				$(this).closest('.checker > span').addClass('checked');
			}
		});
	});	
});
