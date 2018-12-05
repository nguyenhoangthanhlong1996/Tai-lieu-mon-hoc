#Bài 3: Tim uoc so chung lon nhat cua 2 so nguyen a va b
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
		#kiem tra xem a va b deu = 0
		add $t0,$a1,$a2
		beqz $t0,a_b_bang_khong
	de_quy:
		rem $t0,$a1,$a2
		#luu t0 va dia chi tra ve trong ham cha
		addi $sp,$sp,-12
		sw $a1,0($sp)
		sw $a2,4($sp)
		sw $ra,8($sp)
		#kiem tra dung de quy
		bnez $t0,L
		#tra ve ket qua
		move $v1,$a2
		#khoi phuc gia tri con tro sp
		addi $sp,$sp,12
		jr $ra
	L:
		move $a1,$a2
		move $a2,$t0
		jal uoc_chung_lon_nhat
	a_b_bang_khong:
		#xuat chuoi 4
		la $a0,chuoi4
		li $v0,4
		syscall
		jr $ra