#Bài 5: Tính S(n) = 1-2+3-4+...+((-1)^(n+1)))n v?i n > 0
.data
	chuoi1: .asciiz "Nhap n: "
	chuoi2: .asciiz "Ket qua: "
.text
	main:
		jal nhap_n
		jal tinh_S
		#xuat chuoi 2
		li $v0,4
		la $a0,chuoi2
		syscall
		#in ket qua
		li $v0,1
		move $a0,$v1
		syscall
		#ket thuc chuong trinh
		li $v0,10
		syscall
	#thu tuc nhap n, n duoc luu o $a1
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
	
	#thu tuc Tính S, ket qua luu o $v1
	tinh_S:
		#khoi tao ket qua ban dau = 0
		li $v1,0
	lap:
		#kiem tra dieu kien thoat lap
		blt $a1,1,thoat_lap
		#tinh toan xem n am hay duong
		rem $t0,$a1,2
		mul $t0,$t0,2
		sub $t0,$t0,1
		mul $t0,$t0,$a1
		#c?ng d?n n vào k?t qu?
		add $v1,$v1,$t0
		#giam n xuong 1 don vi
		sub $a1,$a1,1
		j lap
	thoat_lap:
		jr $ra