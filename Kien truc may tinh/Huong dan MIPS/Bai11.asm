#Bài 11 - ?i?u khi?n l?p
.data
	chuoi: .asciiz "xin chao\n"
.text
	li $t1,1 # bi?n ?i?u khi?n l?p
	lap:								# ?k l?p $t1 <= 5 							
		bgt $t1,5,thoat_lap # ?i?u ki?n k?t thúc l?p. ($t1 > 5) ?úng -> thoat_lap

		#in chu?i ra màn hình
		li $v0,4
		la $a0,chuoi
		syscall
		
		addi $t1,$t1,1 # t?ng bi?n ?i?u khi?n lên 1
		
		j lap # nh?y t?i nhãn lap
		
	thoat_lap:
		