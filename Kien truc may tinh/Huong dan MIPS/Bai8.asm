#BT: Vi?t ch??ng trình cho phép nh?p tên và tu?i, sau ?ó in ra màn hình
.data
	chuoi1: .asciiz "Nhap ten: "
	chuoi2: .asciiz "Nhap tuoi: "
	ten: .asciiz
.text
	#hien thi chuoi 1
	li $v0,4
	la $a0,chuoi1
	syscall
	#nhap ten
	li $v0,8 
	la $a0,ten 
	li $a1,50
	syscall
	move $t1,$a0
	#hien thi chuoi 2
	li $v0,4
	la $a0,chuoi2
	syscall
	#nhap tuoi
	li $v0,5
	syscall
	move $t2,$v0
	#in ten ra man hinh
	li $v0,4
	move $a0,$t1
	syscall
	#in tuoi ra man hinh
	li $v0,1
	move $a0,$t2
	syscall