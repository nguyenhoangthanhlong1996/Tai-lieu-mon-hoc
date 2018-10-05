.data
	str1: .asciiz "Nhap n: "
	str2: .asciiz "! = "
	newLine: .asciiz "\n"
.text:
	main:
		jal nhap_n
		jal tinh_giai_thua
		jal xuat_ket_qua
		li $v0,10
		syscall
	nhap_n:
		#hien thi chuoi 1:
		li $v0,4
		la $a0,str1
		syscall
		#nguoi dung nhap
		li $v0,5
		syscall
		move $t1,$v0 #luu n vao $t1
		jr $ra
	tinh_giai_thua:
		addi $sp,$sp,-4
		sw $t1,0($sp)
		li $t2,1 #luu ket qua
		lap:
			beq $t1,0,thoat_lap
			mul $t2,$t2,$t1
			addi $t1,$t1,-1
			j lap
		thoat_lap:
			mul $t2,$t2,1
		lw $t1,0($sp)
		addi $sp,$sp,4
		jr $ra
	xuat_ket_qua:
		#hien thi n
		li $v0,1
		move $a0,$t1
		syscall
		#hien thi chuoi 2
		li $v0,4
		la $a0,str2
		syscall
		#hien thi ket qua
		li $v0,1
		move $a0,$t2
		syscall
		jr $ra
		
	