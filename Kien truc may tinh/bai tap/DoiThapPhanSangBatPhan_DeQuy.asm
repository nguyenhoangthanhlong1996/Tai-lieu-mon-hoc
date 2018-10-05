#de quy - Doi thap phan sang bat phan
.data
	chuoi: .asciiz "Nhap n (thap phan): "
.text
	jal nhap_n
	jal doi_sang_bat_phan
	li $v0,10
	syscall
	
	nhap_n:
		li $v0,5
		syscall
		move $a0,$v0
		jr $ra
		
	doi_sang_bat_phan:
		li $s1,8
		dequy:
			addi $sp,$sp,-8
			div $a0,$s1
			mfhi $t1
			sw $t1,0($sp)
			sw $ra,4($sp)
			mflo $a0
			bnez $a0,L
			jr $ra
		L:
			jal dequy
			lw $t1,0($sp)
			lw $ra,4($sp)
			addi $sp,$sp,8
			#in ra man hinh
			li $v0,1
			move $a0,$t1
			syscall
			jr $ra
		