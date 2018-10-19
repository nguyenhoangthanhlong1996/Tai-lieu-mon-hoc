#de quy - Doi thap phan sang thap luc
.data
	chuoi: .asciiz "Nhap n (thap phan): "
.text
	jal nhap_n
	jal doi_sang_thap_luc
	li $v0,10
	syscall
	
	nhap_n:
		li $v0,5
		syscall
		move $a0,$v0
		jr $ra
		
	doi_sang_thap_luc:
		li $s1,16
		dequy:
			addi $sp,$sp,-12
			div $a0,$s1
			mfhi $t1
			sw $t1,0($sp)
			sw $ra,4($sp)
			bgt $t1,9,luu_dang_ki_tu #Kiem tra neu phan du > 9 thi se luu dang ky tu
			#luu dang so 
			li $t2,0 #co hieu thong bao luu tru dang so
			sw $t2,8($sp)
			mflo $a0
			bnez $a0,L
			jr $ra 
		luu_dang_ki_tu:
			#luu dang ky tu
			li $t2,1 #co hieu thong bao luu tru dang ky tu
			sw $t2,8($sp)
			mflo $a0
			bnez $a0,L
			jr $ra 
		L:
			jal dequy
			#in ket qua
			lw $t1,0($sp)
			lw $ra,4($sp)
			lw $t2,8($sp)
			bnez $t2,in_dang_ki_tu #kiem tra co hieu
			#lenh in so
			li $v0,1
			move $a0,$t1
			syscall
			addi $sp,$sp,12
			jr $ra
			
		in_dang_ki_tu:
			addi $t1,$t1,55 # (10 -> 15 = A -> F, ky tu A trong bang ma ASCII la 65 nen ta cong them 55
			#lenh in ky tu
			li $v0,11
			move $a0,$t1
			syscall
			addi $sp,$sp,12
			jr $ra
			
			
		
