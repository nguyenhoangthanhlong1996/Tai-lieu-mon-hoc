#Bài 14 - L?nh sw(store word), lw(load word)
.data
	list: .word 5,10,4,3,7
.text
	#store word
	#li $t1,10
	#li $t2, 268500992
	#sw $t1, 8($t2)
	
	#load word
	la $t1,list
	lw $t2,8($t1)