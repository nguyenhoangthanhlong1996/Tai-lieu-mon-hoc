#Tìm so lon nhat trong 3 so
.data
	chuoi1: .asciiz "Nhap so a: "
	chuoi2: .asciiz "Nhap so b: "
	chuoi3: .asciiz "Nhap so c: "
	chuoi4: .asciiz "So lon nhat la: "
.text
	main:
		jal nhap_a_b_c
		jal so_lon_nhat
		#ket thuc chuong trinh
		li $v0,10
		syscall
	
	#thu tuc nhap a b c, a luu o $a1, b luu o $a2, c luu o $a3
	nhap_a_b_c:
		#xuat chuoi 1
		li $v0,4
		la $a0,chuoi1
		syscall
		#nhan a tu nguoi dung nhap
		li $v0,5
		syscall
		move $a1,$v0
		#xuat chuoi 2
		li $v0,4
		la $a0,chuoi2
		syscall
		#nhan b tu nguoi dung nhap
		li $v0,5
		syscall
		move $a2,$v0
		#xuat chuoi 2
		li $v0,4
		la $a0,chuoi2
		syscall
		#nhan c tu nguoi dung nhap
		li $v0,5
		syscall
		move $a3,$v0
		jr $ra
		
	#tim so lon nhat, truyen a vao $a1, b vao $a2, c vao $a3, ket qua luu o $v1
	so_lon_nhat:
		#gia su so a la lon nhat
		move $v1,$a1
		blt $v1,$a2,b_lon_hon_max
		blt $v1,$a3,c_lon_hon_max
		jr $ra
	b_lon_hon_max:
		move $v1,$a2
		blt $v1,$a3,c_lon_hon_max
		jr $ra
	c_lon_hon_max:	
		move $v1,$a3
		jr $ra
