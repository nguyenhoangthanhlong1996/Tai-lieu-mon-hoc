#Bài 13 - L?nh sb(store byte), lb(load byte)
.data
	chuoi: .asciiz "xin chao"
.text
	#store byte
	#li $t1,'b'
	#sb giá_tr?,offset(address)  =    offset + address  
	#li $t2, 268500992
	#sb $t1, 2($t2)
	
	#load byte
	la $t1,chuoi
	lb $t2,1($t1)