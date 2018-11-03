.data
	chuoi1: .asciiz "Nhap vao chuoi nhi phan (toi da 32 bit): "
	chuoi2: .asciiz "Chuoi vua nhap khong hop le"
	chuoi3: .asciiz "Ket qua khi doi sang thap phan: "
	input_binary: .asciiz
.text
	main:
		jal nhap_chuoi_nhi_phan
		jal kiem_tra_hop_le
		bne $t0,1,thuc_hien_chuyen_doi
		
		li $v0,10
		syscall
	nhap_chuoi_nhi_phan:
		li $v0,8
		la $a0,input_binary
		li $a1,33
		syscall
		jr $ra
	kiem_tra_hop_le:
		la $t1,input_binary
		li $s0,0
		lap:
			lb $t2,($t1)
			beq $t2,10,thoat_lap
			beq $t2,48,hop_le
			beq $t2,49,hop_le
			li $t0,1
			li $v0,4
			la $a0,chuoi2
			syscall
			j thoat_lap
		hop_le:
			addi $t1,$t1,1
			addi $s0,$s0,1
			j lap
		thoat_lap:
			jr $ra
	thuc_hien_chuyen_doi:
		jal doi_sang_thap_phan
		li $v0,10
		syscall
	doi_sang_thap_phan:
		addi $t1,$t1,-1
		li $s1,1
		li $s2,0
		lap_chuyen_doi:
			blez $s0,thoat_lap_chuyen_doi
			lb $t3, ($t1)
			beq $t3,48,khong_tinh
			add $s2,$s2,$s1
			addi $s0,$s0,-1
			addi $t1,$t1,-1
			mul $s1,$s1,2
			j lap_chuyen_doi
		khong_tinh:
			addi $s0,$s0,-1
			addi $t1,$t1,-1
			mul $s1,$s1,2
			j lap_chuyen_doi
		thoat_lap_chuyen_doi:
			li $v0,4
			la $a0,chuoi3
			syscall
			li $v0,1
			move $a0,$s2
			syscall
			jr $ra
		
