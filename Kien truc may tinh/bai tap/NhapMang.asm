#Nhap mang
.data
	str1: .asciiz "Nhap n: "
	str2: .asciiz "Nhap so thu "
	str3: .asciiz " : "
	str4: .asciiz "Mang cac so vua nhap la: "
	newLine: .asciiz "\n"
.text
	main:
		li $v0,4
		la $a0,str1
		syscall
		#nguoi dung nhap
		li $v0,5
		syscall
		move $t1,$v0
		#Tinh bo nho can dung va xin cap phat bo nho
		mul $a0,$t1,4
		li $v0,9
		syscall
		
		li $t2,0 #bien dem
		move $t3, $v0 #index for input
		move $t4, $v0 #index for ouput 
		
		#goi ham input va ouput
		jal input
		jal output
		
		#ket thuc chuong trinh
		li $v0,10
		syscall
		
	input:
		loop_input:
			bge $t2,$t1,exit_input
			#hien thi chuoi 2
			li $v0,4
			la $a0,str2
			syscall
			#hien thi thu tu so
			li $v0,1
			addi $a0,$t2,1
			syscall
			#hien thi chuoi 3
			li $v0,4
			la $a0,str3
			syscall
			#nguoi dung nhap
			li $v0,5
			syscall
			sw $v0,0($t3)
			addi $t2,$t2,1
			addi $t3,$t3,4
			j loop_input
		exit_input:		
		jr $ra
	output:
		#hien thi chuoi 4
		li $v0,4
		la $a0,str4
		syscall
		#hien thi dong moi
		li $v0,4
		la $a0,newLine
		syscall
		li $t2,0 #bien dem
		loop_output:
			bge $t2,$t1,exit_output
			#hien thi so
			li $v0,1
			lw $a0,0($t4)
			syscall
			#hien thi dong moi
			li $v0,4
			la $a0,newLine
			syscall
			#cap nhat gia tri bien dem va index
			addi $t2,$t2,1
			addi $t4,$t4,4
			#goi lap
			j loop_output
		exit_output:
		jr $ra
			
		
	
		
		
	
	
		
