#B�i 10 - Thao t�c ?i?u khi?n r? nh�nh
.data
	chuoi1: .asciiz "bang nhau"
	chuoi2: .asciiz "khong bang nhau"
.text
	li $t1,10
	li $t2,5
	
	#r? nh�nh c� ?i?u ki?n
	beq $t1,$t2,in_chuoi_1 # ($t1 = $t2) ?�ng -> in_chuoi_1
	
	#in chu?i 2
	li $v0,4
	la $a0,chuoi2
	syscall
	
	#r? nh�nh kh�ng ?i?u ki?n
	#b in_chuoi_1

	#k?t th�c ch??ng tr�nh
	li $v0,10
	syscall

	in_chuoi_1:
		#in chu?i 1
		li $v0,4
		la $a0,chuoi1
		syscall	