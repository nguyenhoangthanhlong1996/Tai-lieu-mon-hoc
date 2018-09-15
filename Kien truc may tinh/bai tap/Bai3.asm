.data
	str1: .asciiz "Nhap so thu 1: "
	str2: .asciiz "Nhap so thu 2: "
	str3: .asciiz "Nhap so thu 3: "
	str4: .asciiz "So lon nhat la: "
.text
main:
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
	#Nguoi dung nhap so thu 3
	li $v0,5
	syscall
	add $t3,$zero,$v0 #luu so thu 3 vao $t3
	#So sanh
	bge $t1,$t2,num1_greater_or_equal_num2
	bge $t2,$t3,num2_greater_or_equal_num3
	bge $t3,$t1,num3_greater_or_equal_num1
	#Ket thuc chuong trinh
	li $v0,10
	syscall
num1_greater_or_equal_num2:
	bge $t1,$t3,num1_is_max
	#Ket thuc chuong trinh
	li $v0,10
	syscall
num2_greater_or_equal_num3:
	bge $t2,$t1,num2_is_max
	#Ket thuc chuong trinh
	li $v0,10
	syscall
num3_greater_or_equal_num1:
	bge $t3,$t2,num3_is_max
	#Ket thuc chuong trinh
	li $v0,10
	syscall
num1_is_max:
	#Hien thi chuoi 4
	li $v0,4
	la $a0, str4
	syscall
	#Hien thi so lon nhat
	li $v0,1
	move $a0,$t1
	syscall
	#Ket thuc chuong trinh
	li $v0,10
	syscall
num2_is_max:
	#Hien thi chuoi 4
	li $v0,4
	la $a0, str4
	syscall
	#Hien thi so lon nhat
	li $v0,1
	move $a0,$t2
	syscall
	#Ket thuc chuong trinh
	li $v0,10
	syscall
num3_is_max:
	#Hien thi chuoi 4
	li $v0,4
	la $a0, str4
	syscall
	#Hien thi so lon nhat
	li $v0,1
	move $a0,$t3
	syscall
	#Ket thuc chuong trinh
	li $v0,10
	syscall