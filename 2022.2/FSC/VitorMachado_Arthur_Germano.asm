#Vitor Machado e Arthur Germano e Pedro Lunelli

.data
	msgPRI: .asciiz "Digite a 1� posi��o do vetor\n"
	msgULT: .asciiz "Digite a �ltima posi��o do vetor\n"
	msgVAL: .asciiz "Digite o valor a ser encontrado\n"
	msgINFO: .asciiz "Por padr�o nosso vetor atual �:\n-5, -1, 5, 9, 12, 15, 21, 29, 31, 58, 259, 325.\n(Caso n�o seja poss�vel realizar sua opera��o, a posi��o retornada ser� -1)\n"
	msgRESULT: .asciiz " � a posi��o em que foi encontrado o n�mero!"
	vetor: .word -5 -1 5 9 12 15 21 29 31 58 259 325
.text
.globl main

main:
	li $v0,4
	la $a0,msgINFO			#printa informa��es sobre o vetor
	syscall
	
	li $v0,4
	la $a0,msgPRI			#pede a 1� posi��o a ser buscada
	syscall
	
	li $v0,5			#recebe o valor
	syscall
	
	move $a1,$v0			#salva a 1� posi��o em $a1
	
	li $v0,4
	la $a0,msgULT			#pede a �ltima posi��o do vetor
	syscall
	
	li $v0,5			#recebe o valor
	syscall
	
	move $a2,$v0			#salva a �ltima posi��o em $a2
	
	li $v0,4
	la $a0,msgVAL			#pede o valor a ser encontrado
	syscall
	
	li $v0,5			#recebe o valor
	syscall
	
	move $a3,$v0			#salva o valor a ser buscado em $a3
	
	la $a0,vetor	
			
	addiu $sp,$sp,-20		#reservando 20 bytes para armazenar as informa��es
	
	sw $a0,0($sp)			#passa argumentos como par�metro para PILHA
	sw $a1,4($sp)
	sw $a2,8($sp)
	sw $a3,12($sp)
																																									
	jal BinSrch
	
	
	move $a0,$v0			#passa $v0 para $a0 para impress�o
	
	li $v0,1			#printa o resultado
	syscall
	
	li $v0,4			#printa a mensagem de resultado
	la $a0,msgRESULT
	syscall
	
	li $v0,10			#encerra o programa
	syscall
	

	#$a0 = posi��o mem�ria vetor	$a1 = 1� posi��o	$a2 = �ltima posi��o	$a3 = valor a ser buscado
	
	
	BinSrch:
		sw $ra,16($sp) 			#armazena $ra na sp
		
		lw $t0,0($sp)  			#$t0 � a posi��o de mem�ria do vetor
		lw $t1,4($sp)			#t1 � 1� pos 
		lw $t2,8($sp)			#t2 � a 2� pos
		lw $t3,12($sp)			#valor a ser buscado
				
		
		bgt $t1, $t2, invalid
		
		#posi��o meio salvo em $t4  ($t5 = subtra��o de ULT-PRI e $t6 = 2 para divis�o por 2) $t4=(PRI-ULT)/2
		li $t6,2
		add $t5,$t2,$t1
		div $t5,$t6
		mflo $t4
		
						
		mul $t5,$t4,4			#salva em $t5 o endere�o de mem�ria do meio do vetor a partir do come�o do vetor
		
		add $t5,$t5,$t0			#seta t5 com o endere�o de mem�ria que ser� buscado (meio do vetor, a partir da posi��o m�nima dada pelo usu�rio)
		
		
		addiu $sp,$sp,-20		#reserva mais 5 posi��es de mem�ria para armazenar as infos
		
		
		#$t7 armazena o valor armazenado na posi��o meio
		lw $t7,($t5)
		
		beq $t7,$t3,achei		#termo do meio IGUAL ao termo buscado
		
		bgt $t3,$t7,menor		#termo do meio MENOR que o termo buscado
		
		bgt $t7,$t3,maior		#termo do meio MAIOR que o termo buscado
		
		menor:
			addi $t4,$t4,1
			
			sw $t0,0($sp)		#$t0 � a posi��o de mem�ria do vetor
			sw $t4,4($sp)		# posi��o do meio se torna a primeira posi��o a se buscar
			sw $t2,8($sp)		# ultima posi��o se mant�m a mesma
			sw $t3,12($sp)		# n�mero buscado continua o mesmo
			
			jal BinSrch		#chama novamente a fun��o com os novos par�metros
			
			addiu $sp,$sp,20
			sw $zero,0($sp)		#LIMPA PILHA
			sw $zero,4($sp)		#LIMPA PILHA
			sw $zero,8($sp)		#LIMPA PILHA
			sw $zero,12($sp)	#LIMPA PILHA
			
			lw $ra,16($sp) 		#seta $ra como endere�o de retorno
			sw $zero,16($sp)	#LIMPA PILHA
			jr $ra
			
		maior:
			addi $t4,$t4,-1
			
			sw $t0,0($sp)  		#t0 � a posi��o de mem�ria do vetor
			sw $t1,4($sp)		# primeira posi��o se mant�m a mesma
			sw $t4,8($sp)		# posi��o do meio se torna a ultima posi��o a se buscar
			sw $t3,12($sp)		# n�mero buscado continua o mesmo
			
			jal BinSrch		#chama novamente a fun��o com novos par�metros
			
			addiu $sp,$sp,20
			sw $zero,0($sp)		#LIMPA PILHA
			sw $zero,4($sp)		#LIMPA PILHA
			sw $zero,8($sp)		#LIMPA PILHA
			sw $zero,12($sp)	#LIMPA PILHA
			
			lw $ra,16($sp) #seta $ra como endere�o de retorno
			sw $zero,16($sp)	#LIMPA PILHA
			jr $ra
			
		achei:
			addiu $sp,$sp,20
			sw $zero,0($sp)		#LIMPA PILHA
			sw $zero,4($sp)		#LIMPA PILHA
			sw $zero,8($sp)		#LIMPA PILHA
			sw $zero,12($sp)	#LIMPA PILHA
			
			lw $ra,16($sp) 		#seta $ra como endere�o de retorno
			sw $zero,16($sp)	#LIMPA PILHA
			move $v0,$t4		#move a posi��o do vetor encontrada para $v0
			jr $ra	
		
		invalid:
			li $v0,-1		#seta valor de retorno em $v0=-1
			
			sw $zero,0($sp)		#LIMPA PILHA
			sw $zero,4($sp)		#LIMPA PILHA
			sw $zero,8($sp)		#LIMPA PILHA
			sw $zero,12($sp)	#LIMPA PILHA
			
			lw $ra,16($sp) 		#seta $ra como endere�o de retorno
			sw $zero,16($sp)	#LIMPA PILHA
			
			jr $ra			#volta para $ra
