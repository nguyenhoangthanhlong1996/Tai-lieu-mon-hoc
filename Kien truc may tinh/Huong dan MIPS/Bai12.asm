#B�i 12 - H�m th? t?c
.data
	chuoi: .asciiz "Xin chao"
.text

	main:
		jal in_ra_man_hinh
		
		#ket thuc chuong trinh
		li $v0,10
		syscall
	in_ra_man_hinh:
		#in ra man hinh
		li $v0,4
		la $a0,chuoi
		syscall
		jr $ra