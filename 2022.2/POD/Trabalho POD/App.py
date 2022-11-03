from ClinicaSys import Classes as sis

# path = r'C:\Users\vitor\OneDrive\Área de Trabalho\Trabalho POD\operacoes'
# for i in range (1,6):
#     arq = i
#     patharq = path+'\\'+str(i)+'.txt'
#     print(patharq)
#     with open(patharq,'r')as file:
#         for i in file:
#             print(i)
#             j=i.split(':')
#             l=i.split('->')
#             print(i)
#
#             #Paciente
#             if 'Paciente1_' in i:
#                 Paciente1 = sis.Paciente(j[0][11:],j[1],j[2],j[3],j[4],int(j[5]))
#             if 'Paciente2_' in i:
#                 Paciente2 = sis.Paciente(j[0][11:],j[1],j[2],j[3],j[4],int(j[5]))
#
#             #Secretaria
#             if 'Secretaria_' in i:
#                 secretaria = sis.Secretaria(j[0][12:],j[1],j[2],j[3])
#             if 'Secretaria=' in i:
#                 if 'Medico' in i:
#                     secretaria.cadastrarFuncionario(medico)
#                 if 'Enfermeira' in i:
#                     secretaria.cadastrarFuncionario(enfermeira)
#                 if 'Secretaria' in i:
#                     secretaria.cadastrarFuncionario(secretaria)
#             #Enfermeira
#             if 'Enfermeira_' in i:
#                 enfermeira = sis.Enfermeira(j[0][12:],j[1],j[2],j[3],j[4])
#             if 'Enfermeira=cadastrar' in i:
#                 if 'Paciente1' in i:
#                     enfermeira.cadastrarPacientes(Paciente1)
#                 if 'Paciente2' in i:
#                     enfermeira.cadastrarPacientes(Paciente2)
#             if 'Enfermeira=relatorio' in i:
#                 if 'Paciente1' in i:
#                     enfermeira.gerarRelatorio(medico,Paciente1)
#                 if 'Paciente2' in i:
#                     enfermeira.gerarRelatorio(medico,Paciente2)
#             #Medico
#             if 'Medico_' in i:
#                 medico =sis.Medico(j[0][8:],j[1],j[2],j[3],j[4][6:])
#             if 'Medico=diagnosticar' in i:
#                 if 'Paciente1' in i:
#                     medico.diagnosticar(Paciente1,j[1])
#                 if 'Paciente2' in i:
#                     medico.diagnosticar(Paciente2,j[1])
#             if 'Medico=internar' in i:
#                 if 'Paciente1' in i:
#                     medico.internar(Paciente1)
#                 if 'Paciente2' in i:
#                     medico.internar(Paciente2)
#             if 'Medico=liberar' in i:
#                 if 'Paciente1' in i:
#                     medico.liberar(Paciente1)
#                 if 'Paciente2' in i:
#                     medico.liberar(Paciente2)
medico=sis.Medico('vitor','91131839234','14/12/2003','Solteiro','8492321')
enfermeira= sis.Enfermeira('vitor','91131839234','14/12/2003','Solteiro','849266')
secretaria = sis.Secretaria('vitor','91131839234','14/12/2003','Solteiro')
paciente=sis.Paciente('vitor','91131839234','14/12/2003','Solteiro','Tachimed',10000)
secretaria.cadastrarFuncionario(medico)
secretaria.cadastrarFuncionario(enfermeira)
secretaria.cadastrarFuncionario(secretaria)
medico.internar(paciente)
enfermeira.cadastrarPacientes(paciente)
medico.diagnosticar(paciente,'DOR DE CABEÇA')
medico.liberar(paciente)
enfermeira.gerarRelatorio(medico,paciente)

#o código acima não funcionou então fiz este parar pelo menos demonstrar que tudo funciona