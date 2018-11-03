#Bài 16 - ?? quy
#Ch??ng trình tính n! v?i n >= 0 n l?u trong $a0, k?t qu? tr? v? l?u trong $v0
.data
.text
	main:
		li $a0,4
		jal giai_thua
		
		#ket thuc chuong trinh
		li $v0,10
		syscall
		
	giai_thua:
		addi $sp,$sp,-8
		sw $a0,0($sp)
		sw $ra,4($sp)
		bgt $a0,1,de_quy
		li $v0,1
		jr $ra
	de_quy:
		addi $a0,$a0,-1
		jal giai_thua