try:
    arq = open("test.txt", "wb")
    print(arq.name)
    print(arq.closed)
    print(arq.mode)
    print(arq.readable())
    print(arq.writable())
    print(arq.seekable())
    print(arq.isatty())
    print(arq.fileno())

except Exception as e:
    
    if not arq.closed:
        arq.close()
    print("---",e)
    raise e

#######################################
print("#################################")

nome_arq="test.txt"

with open("test.txt", "w") as arq:
    print(arq.tell())
    arq.write("meu arquivo de teste\n")
    arq.write("NOVALINHA\n")
    linhas = ["linhasX\n", "LINHASY\n"]
    arq.writelines(linhas)
    print(arq.tell())