#B�i 15 - K? thu?t sao l?u d? li?u
.data
.text
	main:
		li $a0,10
		jal func
		#in gi� tr? $a0
		li $v0,1
		syscall
		
		#k?t th�c ch??ng tr�nh
		li $v0,10
		syscall
	func: #in 5 ra m�n h�nh
		addi $sp,$sp,-4
		sw $a0,($sp)
		li $v0,1
		li $a0,5
		syscall
		lw $a0,($sp)
		jr $ra