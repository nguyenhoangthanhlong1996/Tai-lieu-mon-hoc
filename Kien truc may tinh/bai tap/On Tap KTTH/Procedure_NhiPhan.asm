.data
	str: .asciiz "Start function!\n"
.text
	li $v0, 5
	syscall
	
	addi $a0, $v0, 0 # n --> NhiPhan
	addi $a1, $a0, 0 # $a1 = $a0
	
	jal NhiPhan
	j Exit	
NhiPhan:
	# Backup
	addi $sp, $sp, -12	# 3 x -4
	sw $ra, 8($sp)
	sw $a0, 4($sp)
	sw $a1, 0($sp)
	
	# Check finish condition
	# n < 1
	slti $t0, $a0, 1
	beq $t0, $zero, L1
	#bne $a0, $zero, L1	
	# a0 = 0
	
	#RESTORE 01
	lw $ra, 8($sp)
	lw $a0, 4($sp)
	lw $a1, 0($sp)
	addi $sp, $sp, 12
	jr $ra # return
L1: 
	addi $a1, $a0, 0 # a1 = a0
	# tinh n/2
	div $a0, $a0, 2 # n = n/2
	jal NhiPhan # recursive de quy n/2
	
	# tinh so du
	rem $t0, $a1,2 # remainder
	
	li $v0, 1
	addi $a0, $t0, 0
	syscall
	
	# RESTORE 02
	
	lw $ra, 8($sp)
	lw $a0, 4($sp)
	lw $a1, 0($sp)
	addi $sp, $sp, 12
	jr $ra # return
Exit:
	li $v0, 10
	syscall
