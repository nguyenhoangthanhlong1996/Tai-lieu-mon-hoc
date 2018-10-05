.data
	str1: .asciiz "Nhap m: "
	str2: .asciiz "Nhap n: "
.text
	main:
		jal nhap_mn
		jal tinh
		
		li $v0,10
		syscall
	nhap_mn:
		#nhap m
		li $v0,4
		la $a0,str1
		syscall
		li $v0,5
		syscall
		add $t1,$zero,$v0 #$t1 = m
			
		#nhap n
		li $v0,4
		la $a0,str2
		syscall
		li $v0,5
		syscall
		add $t2,$zero,$v0 #$t2 = n
		
		jr $ra
	tinh:
		li $t3,0 #$t3 = 0 (bien SUM)
		lap:
			blez $t1,thoatlap
			blez $t2,thoatlap
			add $t4,$t1,$t2
			add $t3,$t3,$t4
			add $t1,$t1,-1
			add $t2,$t2,-1		
			j lap
		thoatlap:
			#in ket qua
			li $v0,1
			move $a0,$t3
			syscall
		jr $ra
	