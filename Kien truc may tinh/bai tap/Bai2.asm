.data
	str1: .asciiz "Nhap so thu 1: "
	str2: .asciiz "Nhap so thu 2: "
	str3: .asciiz "Tong cua 2 so la: "
.text
	#Hien thi chuoi 1
	li $v0,4
	la $a0, str1
	syscall
	#Nguoi dung nhap so thu 1
	li $v0,5
	syscall
	add $t1,$zero,$v0 #luu so thu 1 vao $t1
	#Hien thi chuoi 2
	li $v0,4
	la $a0, str2
	syscall
	#Nguoi dung nhap so thu 2
	li $v0,5
	syscall
	add $t2,$zero,$v0 #luu so thu 2 vao $t2
	#Hien thi chuoi 3
	li $v0,4
	la $a0, str3
	syscall
	#Hien thi ket qua tinh duoc
	add $t3,$t1,$t2 #$t3 = $t1 + $t2
	li $v0,1
	move $a0,$t3
	syscall
	#Ket thuc chuong trinh
	li $v0,10
	syscall
	