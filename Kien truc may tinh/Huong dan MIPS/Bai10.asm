#Bài 10 - Thao tác ?i?u khi?n r? nhánh
.data
	chuoi1: .asciiz "bang nhau"
	chuoi2: .asciiz "khong bang nhau"
.text
	li $t1,10
	li $t2,5
	
	#r? nhánh có ?i?u ki?n
	beq $t1,$t2,in_chuoi_1 # ($t1 = $t2) ?úng -> in_chuoi_1
	
	#in chu?i 2
	li $v0,4
	la $a0,chuoi2
	syscall
	
	#r? nhánh không ?i?u ki?n
	#b in_chuoi_1

	#k?t thúc ch??ng trình
	li $v0,10
	syscall

	in_chuoi_1:
		#in chu?i 1
		li $v0,4
		la $a0,chuoi1
		syscall	