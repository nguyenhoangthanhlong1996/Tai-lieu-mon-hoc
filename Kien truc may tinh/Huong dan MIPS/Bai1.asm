#Bài 1 - Cách gán s? nguyên cho 1 thanh ghi
#Gán 10 cho thanh ghi $t1
#Gán 15 cho thanh ghi $t2
#Gán 20 cho thanh ghi $t3
.data #ph?n ?? khai báo d? li?u
	number: .word 10
.text #ph?n code chính
	lw $t1,number # l?y giá tr? c?a nhãn number gán vào thanh ghi $t1
	li $t2,15 # $t2 = 15
	addi $t3,$zero,20 # $t3 = 0 + 20