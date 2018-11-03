#Bài 7 - In chu?i ký t? ra màn hình
.data
	chuoi: .asciiz "Xin chao\n"
.text
	la $a0,chuoi
	li $v0,4
	syscall
	
	la $a0,chuoi
	li $v0,4
	syscall
	