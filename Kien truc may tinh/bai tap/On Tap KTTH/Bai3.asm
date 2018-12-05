#B�i 3: Tim uoc so chung lon nhat cua 2 so nguyen a va b
.data
	chuoi1: .asciiz "Nhap so nguyen a: "
	chuoi2: .asciiz "Nhap so nguyen b: "
	chuoi3: .asciiz "Ket qua: "
	chuoi4: .asciiz "Vi a = 0 va b = 0 nen khong co UCLN"
.text
	main:
		jal nhap_a_b
		jal uoc_chung_lon_nhat
		#ket thuc chuong trinh
		li $v0,10
		syscall
		
	#thu tuc nhap 2 so nguyen a,b (a duoc luu trong $a1, a duoc luu trong $a2)
	nhap_a_b:
		#xuat chuoi 1
		la $a0,chuoi1
		li $v0,4
		syscall
		#nhan so a tu nguoi dung nhap
		li $v0,5
		syscall
		move $a1,$v0
		#xuat chuoi 2
		la $a0,chuoi1
		li $v0,4
		syscall
		#nhan so b tu nguoi dung nhap
		li $v0,5
		syscall
		move $a2,$v0
		jr $ra
		
	#thu tuc tim uoc chung lon nhat, tham so a truyen vao $a1, b truyen vao $a2, ket qua duoc luu o $v1
	uoc_chung_lon_nhat:
		#lay gia tri tuyet doi cua a va b
		abs $a1,$a1
		abs $a2,$a2
		#kiem tra xem a va b deu = 0
		add $t0,$a1,$a2  # t = a + b
		beqz $t0,a_b_bang_khong
		beq $a1,0,L
		beq $a2,0,L
	lap:
		beq $a1,$a2,thoat_lap
		bgt $a1,$a2,a_lon_hon_b
		sub $a2,$a2,$a1
		j lap
	a_lon_hon_b:
		sub $a1,$a1,$a2
		j lap
	thoat_lap:
		move $v1,$a1
		#xuat chuoi 3
		la $a0,chuoi3
		li $v0,4
		syscall
		#in ket qua
		li $v0,1
		move $a0,$v1
		syscall
		jr $ra
	L:
		add $v1,$a1,$a2	
		#xuat chuoi 3
		la $a0,chuoi3
		li $v0,4
		syscall
		#in ket qua
		li $v0,1
		move $a0,$v1
		syscall
		jr $ra
	a_b_bang_khong:
		#xuat chuoi 4
		la $a0,chuoi4
		li $v0,4
		syscall
		jr $ra
