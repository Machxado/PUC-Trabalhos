#Vitor Machado e Arthur Germano e Pedro Lunelli

.data
	msgPRI: .asciiz "Digite a 1° posição do vetor\n"
	msgULT: .asciiz "Digite a última posição do vetor\n"
	msgVAL: .asciiz "Digite o valor a ser encontrado\n"
	msgINFO: .asciiz "Por padrão nosso vetor atual é:\n-5, -1, 5, 9, 12, 15, 21, 29, 31, 58, 259, 325.\n(Caso não seja possível realizar sua operação, a posição retornada será -1)\n"
	msgRESULT: .asciiz " é a posição em que foi encontrado o número!"
	vetor: .word -5 -1 5 9 12 15 21 29 31 58 259 325
.text
.globl main

main:
	li $v0,4
	la $a0,msgINFO			#printa informações sobre o vetor
	syscall
	
	li $v0,4
	la $a0,msgPRI			#pede a 1° posição a ser buscada
	syscall
	
	li $v0,5			#recebe o valor
	syscall
	
	move $a1,$v0			#salva a 1° posição em $a1
	
	li $v0,4
	la $a0,msgULT			#pede a última posição do vetor
	syscall
	
	li $v0,5			#recebe o valor
	syscall
	
	move $a2,$v0			#salva a última posição em $a2
	
	li $v0,4
	la $a0,msgVAL			#pede o valor a ser encontrado
	syscall
	
	li $v0,5			#recebe o valor
	syscall
	
	move $a3,$v0			#salva o valor a ser buscado em $a3
	
	la $a0,vetor	
			
	addiu $sp,$sp,-20		#reservando 20 bytes para armazenar as informações
	
	sw $a0,0($sp)			#passa argumentos como parâmetro para PILHA
	sw $a1,4($sp)
	sw $a2,8($sp)
	sw $a3,12($sp)
																																									
	jal BinSrch
	
	
	move $a0,$v0			#passa $v0 para $a0 para impressão
	
	li $v0,1			#printa o resultado
	syscall
	
	li $v0,4			#printa a mensagem de resultado
	la $a0,msgRESULT
	syscall
	
	li $v0,10			#encerra o programa
	syscall
	

	#$a0 = posição memória vetor	$a1 = 1° posição	$a2 = última posição	$a3 = valor a ser buscado
	
	
	BinSrch:
		sw $ra,16($sp) 			#armazena $ra na sp
		
		lw $t0,0($sp)  			#$t0 é a posição de memória do vetor
		lw $t1,4($sp)			#t1 é 1° pos 
		lw $t2,8($sp)			#t2 é a 2³ pos
		lw $t3,12($sp)			#valor a ser buscado
				
		
		bgt $t1, $t2, invalid
		
		#posição meio salvo em $t4  ($t5 = subtração de ULT-PRI e $t6 = 2 para divisão por 2) $t4=(PRI-ULT)/2
		li $t6,2
		add $t5,$t2,$t1
		div $t5,$t6
		mflo $t4
		
						
		mul $t5,$t4,4			#salva em $t5 o endereço de memória do meio do vetor a partir do começo do vetor
		
		add $t5,$t5,$t0			#seta t5 com o endereço de memória que será buscado (meio do vetor, a partir da posição mínima dada pelo usuário)
		
		
		addiu $sp,$sp,-20		#reserva mais 5 posições de memória para armazenar as infos
		
		
		#$t7 armazena o valor armazenado na posição meio
		lw $t7,($t5)
		
		beq $t7,$t3,achei		#termo do meio IGUAL ao termo buscado
		
		bgt $t3,$t7,menor		#termo do meio MENOR que o termo buscado
		
		bgt $t7,$t3,maior		#termo do meio MAIOR que o termo buscado
		
		menor:
			addi $t4,$t4,1
			
			sw $t0,0($sp)		#$t0 é a posição de memória do vetor
			sw $t4,4($sp)		# posição do meio se torna a primeira posição a se buscar
			sw $t2,8($sp)		# ultima posição se mantém a mesma
			sw $t3,12($sp)		# número buscado continua o mesmo
			
			jal BinSrch		#chama novamente a função com os novos parâmetros
			
			addiu $sp,$sp,20
			sw $zero,0($sp)		#LIMPA PILHA
			sw $zero,4($sp)		#LIMPA PILHA
			sw $zero,8($sp)		#LIMPA PILHA
			sw $zero,12($sp)	#LIMPA PILHA
			
			lw $ra,16($sp) 		#seta $ra como endereço de retorno
			sw $zero,16($sp)	#LIMPA PILHA
			jr $ra
			
		maior:
			addi $t4,$t4,-1
			
			sw $t0,0($sp)  		#t0 é a posição de memória do vetor
			sw $t1,4($sp)		# primeira posição se mantém a mesma
			sw $t4,8($sp)		# posição do meio se torna a ultima posição a se buscar
			sw $t3,12($sp)		# número buscado continua o mesmo
			
			jal BinSrch		#chama novamente a função com novos parâmetros
			
			addiu $sp,$sp,20
			sw $zero,0($sp)		#LIMPA PILHA
			sw $zero,4($sp)		#LIMPA PILHA
			sw $zero,8($sp)		#LIMPA PILHA
			sw $zero,12($sp)	#LIMPA PILHA
			
			lw $ra,16($sp) #seta $ra como endereço de retorno
			sw $zero,16($sp)	#LIMPA PILHA
			jr $ra
			
		achei:
			addiu $sp,$sp,20
			sw $zero,0($sp)		#LIMPA PILHA
			sw $zero,4($sp)		#LIMPA PILHA
			sw $zero,8($sp)		#LIMPA PILHA
			sw $zero,12($sp)	#LIMPA PILHA
			
			lw $ra,16($sp) 		#seta $ra como endereço de retorno
			sw $zero,16($sp)	#LIMPA PILHA
			move $v0,$t4		#move a posição do vetor encontrada para $v0
			jr $ra	
		
		invalid:
			li $v0,-1		#seta valor de retorno em $v0=-1
			
			sw $zero,0($sp)		#LIMPA PILHA
			sw $zero,4($sp)		#LIMPA PILHA
			sw $zero,8($sp)		#LIMPA PILHA
			sw $zero,12($sp)	#LIMPA PILHA
			
			lw $ra,16($sp) 		#seta $ra como endereço de retorno
			sw $zero,16($sp)	#LIMPA PILHA
			
			jr $ra			#volta para $ra
