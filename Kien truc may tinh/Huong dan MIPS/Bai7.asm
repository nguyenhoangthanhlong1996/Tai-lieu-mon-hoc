#B�i 7 - In chu?i k� t? ra m�n h�nh
.data
	chuoi: .asciiz "Xin chao\n"
.text
	la $a0,chuoi
	li $v0,4
	syscall
	
	la $a0,chuoi
	li $v0,4
	syscall
	