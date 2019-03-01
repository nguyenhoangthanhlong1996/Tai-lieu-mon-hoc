#Tinh fact mo rong n m
.data
	chuoi1: .asciiz "Nhap n: "
	chuoi2: .asciiz "Nhap m: "
	chuoi3: .asciiz "Ket qua: "
.text
	main:
		jal nhap_n_m
		jal T
		#xuat chuoi 3
		li $v0,4
		la $a0,chuoi3
		syscall
		#in ket qua
		li $v0,1
		move $a0,$v1
		syscall
		#ket thuc chuong trinh
		li $v0,10
		syscall
	
	#thu tuc nhap n m, n duoc luu o $a1, m luu o $a2
	nhap_n_m:
		#xuat chuoi 1
		li $v0,4
		la $a0,chuoi1
		syscall
		#nhan n tu nguoi dung nhap
		li $v0,5
		syscall
		move $a1,$v0
		#xuat chuoi 2
		li $v0,4
		la $a0,chuoi2
		syscall
		#nhan m tu nguoi dung nhap
		li $v0,5
		syscall
		move $a2,$v0
		jr $ra
		
	#thu tuc tinh FactT, truyen tham so n vao $a1, m vao $a2, ket qua luu o $v1
	T:
		#luu n, m va dia chi tra ve trong ham cha
		addi $sp,$sp,-12
		sw $a1,0($sp)
		sw $a2,4($sp)
		sw $ra,8($sp)
		#kiem tra dung de quy
		bge $a1,1,L
		#tro ve ham cha de tinh ket qua
		li $v1,1
		addi $sp,$sp,12
		jr $ra
	L:
		#n-- va m--
		addi $a1,$a1,-1
		addi $a2,$a2,-1
		jal T
		#lay ra n,m va dia chi se tro ve
		lw $a1,0($sp)
		lw $a2,4($sp)
		lw $ra,8($sp)
		#tinh ket qua
		add $t0,$a1,$a2
		add $v1,$v1,$t0
		addi $sp,$sp,12
		jr $ra
		
		
