.data
	vetor:.word 5 2 3 4 6

.text 
.globl main

	main:
		la $a0,vetor
		lw $t1,($a0)
		
		sw $a0,0($sp)
		
		move $a0,$t1
		
		li $v0,1
		syscall