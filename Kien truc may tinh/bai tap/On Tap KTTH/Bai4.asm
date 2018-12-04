#Bài 4: Tìm th??ng và s? d? c?a phép chia
.data
	chuoi1: .asciiz "Nhap so bi chia: "
	chuoi2: .asciiz "Nhap so chia: "
	chuoi3: .asciiz "Thuong: "
	chuoi4: .asciiz "\nDu: "
.text
	main:
		jal nhap_sbc_sc
		jal tim_thuong_du
		#copy thuong va du sang thanh ghi khac
		move $t1,$v0
		move $t2,$v1
		#xuat chuoi 3
		li $v0,4
		la $a0,chuoi3
		syscall
		#xuat ket qua thuong
		li $v0,1
		move $a0,$t1
		syscall
		#xuat chuoi 4
		li $v0,4
		la $a0,chuoi4
		syscall
		#xuat ket qua du
		li $v0,1
		move $a0,$t2
		syscall
		#ket thuc chuong trinh
		li $v0,10
		syscall
	
	#thu tuc nhap so bi chia va so chia, sbc luu o $a1, sc luu o $a2
	nhap_sbc_sc:
		#xuat chuoi 1
		li $v0,4
		la $a0,chuoi1
		syscall
		#nhan so bi chia tu nguoi dung nhap
		li $v0,5
		syscall
		move $a1,$v0
		#xuat chuoi 2
		li $v0,4
		la $a0,chuoi2
		syscall
		#nhan so chia tu nguoi dung nhap
		li $v0,5
		syscall
		move $a2,$v0
		jr $ra
	
	#thu tuc tim thuong va du, ket qua thuong luu o $v0, du luu o $v1
	tim_thuong_du:
		#khoi tao gia tri ban dau cua du = 0
		li $v0,0
	lap:
		blt $a1,$a2,thoat_lap
		addi $v0,$v0,1
		sub $a1,$a1,$a2
		j lap
	thoat_lap:
		move $v1,$a1
		jr $ra
		
		