.data
	str1: .asciiz "Nhap so nguyen: "
.text
	main:
		jal nhap
		jal tinhtong
		syscall
		li $v0,10
		syscall
	nhap:
		#hien thi chuoi 1
		li $v0,4
		la $a0,str1
		syscall
		#nguoi dung nhap
		li $v0,5
		syscall
		add $t0,$zero,$v0
		jr $ra
	tinhtong:
		li $t1,0 #luu tong
		li $t2,0 #bien dem
		#dieu kien dung
		mul $t3,$t0,2 
		addi $t3,$t3,1
		lap:
			mul $t4,$t2,2 #$t4 = $t2 * 2
			addi $t4,$t4,1 #$t4 = $t4 + 1
			bgt $t4,$t3,thoatlap
			add $t1,$t1,$t4 #$t1 = $t1 + $t4
			addi $t2,$t2,1 #$t2 = $t2 + 1 (tang bien dem len 1 don vi)
			j lap
		thoatlap:
			#in ket qua
			li $v0,1
			move $a0,$t1
		jr $ra
		
		