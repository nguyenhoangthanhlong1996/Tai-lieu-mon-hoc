.data
	str1: .asciiz  "Nhap n:"
	str2: .asciiz  "Tong tu 1 -> "
	str3: .asciiz " la: "
.text
	main:
		jal input_n
		jal sum
	input_n: #Nguoi dung nhap n
		#Hien thi chuoi 1
		li $v0,4
		la $a0,str1
		syscall
		#Nguoi dung nhap
		li $v0,5
		syscall
		add $t1,$zero,$v0 #luu gia tri vao $t1
		jr $ra
	sum: #Tinh tong
		addi $s0,$zero,0 #tong
		addi $s1,$zero,0 #dem
		loop:
			bgt $s1,$t1,exit #neu dem > n thoat vong lap
			add $s0,$s0,$s1 #cong don tong
			addi $s1,$s1,1 #tang dem len 1 don vi
			j loop
		exit: #thoat vong lap, hien thi ket qua
			#Hien thi chuoi 2
			li $v0,4
			la $a0,str2
			syscall
			#Hien thi n
			li $v0,1
			move $a0,$t1
			syscall
			#Hien thi chuoi 3
			li $v0,4
			la $a0,str3
			syscall
			#Hien thi ket qua
			li $v0,1
			move $a0, $s0
			syscall
			#ket thuc chuong trinh
			li $v0,10
			syscall
		jr $ra