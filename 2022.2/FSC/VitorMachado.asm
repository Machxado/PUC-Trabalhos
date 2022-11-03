#TRABALHO EXTRA VITOR MACHADO
.data
	msgNUM:.asciiz "Digite o N�mero que voc� deseja realizar a Potencializar: "
	msgPOT:.asciiz "Digite o tamanho da Pot�ncia: "
	msgRESULT:.asciiz "O resultado �: "

.text
	main:	
		li $v0, 4
		la $a0, msgNUM
		syscall
		
		li $v0, 5       #recebe o valor do n�mero a ser potencializado
		syscall
		
		move $s0, $v0	#armazena o valor a ser elevado em s0
		
		li $v0, 4	
		la $a0, msgPOT
		syscall
		
		li $v0, 5	#recebe o valor da pot�ncia
		syscall
		
		move $s1, $v0	#armazena o valor da pot�ncia em s1
		li $v0,1	#define o valor de $v0 para 1, para come�ar as multiplica��es depois
		
		move $a0,$s0	#passa os argumentos para a chamada
		move $a1,$s1
				
		jal potenciacao
		
		move $s3,$v0	#move resultado pra $s3
		
		li $v0,4	#print mensagem
		la $a0,msgRESULT
		syscall
		
		li $v0,1	#print resultado opera��o
		move $a0,$s3
		syscall
		
		li $v0,10	#fim programa
		syscall
		
	potenciacao:
		li $t0,1		#armazena 1 em $t0, para compara��o
		addiu $sp, $sp, -8	#volta 8 posi��es no stack pointer
		sw $ra, 4($sp)		#armazena a chamada no stack pointer -4 posi��es
		sw $a1, 0($sp)		#armazena o argumento da recursividade "nessa fase" no stack pointer -8
		
		bgt  $a1,$t0,volta	#compara se a pot�ncia � maior que 1, se sim decrementa em volta e chama denovo o fact para comparar
		
		mul $v0,$v0,$a0
		addi $sp,$sp,8
		jr $ra
				
	volta: 
		addiu $a1,$a1,-1	#decrementa a pot�ncia
		jal potenciacao
		
		lw $a1, 0($sp)		#carrega o valor anterior de a0
		lw $ra, 4($sp)		#carrega o endere�o anterior
		add $sp,$sp,8		#volta ainda mais no Stack pointer (vetor de armazenamento)
		mul $v0,$v0,$a0
		jr $ra
