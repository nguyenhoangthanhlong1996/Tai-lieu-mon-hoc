#Bài 5 - Nh?p chu?i
.data
	chuoi: .asciiz 
.text
	li $v0,8
	la $a0,chuoi
	li $a1,5
	syscall