#B�i 11 - ?i?u khi?n l?p
.data
	chuoi: .asciiz "xin chao\n"
.text
	li $t1,1 # bi?n ?i?u khi?n l?p
	lap:								# ?k l?p $t1 <= 5 							
		bgt $t1,5,thoat_lap # ?i?u ki?n k?t th�c l?p. ($t1 > 5) ?�ng -> thoat_lap

		#in chu?i ra m�n h�nh
		li $v0,4
		la $a0,chuoi
		syscall
		
		addi $t1,$t1,1 # t?ng bi?n ?i?u khi?n l�n 1
		
		j lap # nh?y t?i nh�n lap
		
	thoat_lap:
		