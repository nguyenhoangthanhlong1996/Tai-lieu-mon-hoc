#??o ng??c s? nguyên d??ng n
.data
	chuoi1: .asciiz "Nhap n: "
.text
	main:
		jal nhap_n
		jal dao_nguoc_so
		#ket thuc chuong trinh
		li $v0,10
		syscall
	
	#thu tuc nhap n, n luu o $a1
	nhap_n:
		#xuat chuoi 1
		li $v0,4
		la $a0,chuoi1
		syscall
		#nhan n tu nguoi dung nhap
		li $v0,5
		syscall
		move $a1,$v0	
		jr $ra
	
	#thu tuc dao nguoc so, truyen n vao $a1, ket qua luu o $v1
	dao_nguoc_so:
		li $v1,0
	lap:
		#dieu kien thoat lap
		blez $a1,thoat_lap
		#lay ra so cuoi cung luu o $t0
		rem $t0,$a1,10
		mul $v1,$v1,10
		add $v1,$v1,$t0
		div $a1,$a1,10
		j lap
	thoat_lap:
		jr $ra