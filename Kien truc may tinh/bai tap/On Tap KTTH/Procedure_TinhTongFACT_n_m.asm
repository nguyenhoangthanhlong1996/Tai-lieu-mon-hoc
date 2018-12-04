.data
	str: .asciiz "Start function!\n"
.text
	li $v0, 5
	syscall	
	addi $a0, $v0, 0 # n	
	
	li $v0, 5
	syscall	
	addi $a1, $v0, 0 # m
	
	jal TinhTongfactNM	
	
	addi $a0, $v0, 0
	li $v0,1	
	syscall		
	
	j Exit	
TinhTongfactNM:
	# Backup	
	addi $sp, $sp, -12
	sw $ra, 0($sp)
	sw $a0, 4($sp)
	sw $a1, 8($sp)
	# Check finish condition
	bgt $a0, $zero, L1 # < 1		
	# Calculation
	addi $v0, $zero, 1	
	#RESTORE
	lw $ra, 0($sp)
	lw $a0, 4($sp)
	lw $a1, 8($sp)
	addi $sp,$sp,12
	
	jr $ra # return
L1:
	# calc fact(n-1)
	addi $a0, $a0, -1 # modify a0, n
	addi $a1, $a1, -1 # modify a1, m
	jal TinhTongfactNM
	# n + fact(n-1) (v0)
	addi $t0, $v0, 0	
	# khoi phuc a0 --> n 
	lw $ra, 0($sp)
	lw $a0, 4($sp) # a0 = n
	lw $a1, 8($sp) # a0 = n
	addi $sp,$sp,12	
	# tinh n + fact (n-1)
	add $v0, $t0, $a0 # v0 = n + T(n-1,m-1)
	add $v0, $v0, $a1 # v0 = v0 + m
	jr $ra # return
Exit:
	li $v0, 10
	syscall
