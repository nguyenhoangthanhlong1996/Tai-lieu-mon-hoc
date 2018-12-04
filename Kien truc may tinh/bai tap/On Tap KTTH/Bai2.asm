#Bài 2: ??i sang h? nh? phân c?a s? nguyên d??ng n - ?? quy
.data
	chuoi1: .asciiz "Nhap so nguyen duong n: "
	chuoi2: .asciiz "Ket qua doi sang nhi phan: "
.text
	main:
		jal nhap_n
		move $a1,$v0
		jal nhi_phan
		#ket thuc chuong trinh
		li $v0,10
		syscall
		
	#thu tuc nhap vao so nguyen duong n, ket qua duoc luu o $v0
	nhap_n:
		#xuat chuoi 1
		la $a0,chuoi1
		li $v0,4
		syscall
		#nhan n tu nguoi dung
		li $v0,5
		syscall
		jr $ra
		
	#thu tuc doi sang nhi phan, tham so n truyen vao $a1
	nhi_phan:
		#luu n va dia chi tra ve trong ham cha
		addi $sp,$sp,-8
		sw $a1,0($sp)
		sw $ra,4($sp)
		#kiem tra xem n<1
		bge $a1,1,L
		#xuat chuoi 2
		la $a0,chuoi2
		li $v0,4
		syscall
		jr $ra
	L:
		#gan gia tri n = n / 2
		div $a1,$a1,2
		jal nhi_phan
		#khoi phuc n va dia chi tra ve trong ham cha
		addi $sp,$sp,8
		lw $a1,0($sp)
		lw $ra,4($sp)
		#tinh so du va in ra man hinh
		rem $t0,$a1,2
		li $v0,1
		move $a0,$t0
		syscall
		jr $ra