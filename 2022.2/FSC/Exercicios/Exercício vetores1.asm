#void main()

#for(i=0; i<size; i++)
#C[i] = soma( A[], B[], i);

#int soma_vet(int A[], int B[], int i)

#return (A[i]+B[i]);

.data
A: .word 1 2 3 4 5 6
B: .word 1 2 3 4 5 6 
C: .word 0 0 0 0 0 0
pos .word 0
.text 
.globl main
main:
	