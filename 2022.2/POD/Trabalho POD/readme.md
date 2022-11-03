##Trabalho 1 da disciplina de programação orientada a objetos 

•	Autores: @PedroLunelli & @VitorMachado

•	Descrição do trabalho:
Implementar um sistema para gerenciamento de Pacientes, Médicos, Enfermeiras e Secretárias dentro de um contexto hospitalar, no qual cada um 
desses atores apresenta funcionalidades e requisitos específicos e pré-estabelecidos pelo enunciado do trabalho. Todo sistema proposto foi 
implementado como um pacote chamado Clinica, composto de um módulo chamado clinicaSys, no qual apresenta uma Classe para cada tipo de funcionário 
e uma para Pacientes, todas vinculadas a classe abstrata Pessoa 

•	Descrição e operações de cada uma das classes:

-Classe Médico:  Manipulação de dados no arquivo Funcinario.dat; internação, liberação e diagnóstico de Pacientes por meio da manipulação no arquivo
 Pacientes.dat.
-Classe Enfermeira: Manipulação de dados no arquivo Funcionarios.dat; cadastro de pacientes para futura manipulação no arquivo por um médico;
 geração de relatórios específicos de determinado paciente acerca do procedimento realizado no hospital.
-Classe Secretaria: Manipulação de dados no arquivo Funcionarios.dat; cadastro de novas secretarias; cadastro de Enfermeiras (devem possuir 
COREN e o mesmo registro não pode já estar cadastrado em Funcionarios.dat com outra enfermeira); cadastro de Médicos (devem possuir CRM e o mesmo 
registro não pode já estar cadastrado em Funcionarios.dat com outro médico).
-Classe Paciente: Deve possuir a classe Convênio.
-Classe Convênio: Representa o tipo de convênio do paciente, sendo eles: SUS, IPE, Unimed. Os convênios apresentam créditos distintos para os 
pacientes, para que de acordo com o procedimento realizado, sejam-lhe descontados créditos. A exceção é o SUS que apresenta crédito zero e as 
consultas são gratuitas.
-Observação: Todas as classes são subclasses de Pessoa, que é uma classe abstrata que apresenta os métodos de cadastro e obtenção de dados, além
 dos atributos Nome Completo, CPF, data de nascimento, e Estado Civil. Ademais, os dados de CPF, COREN, CRM, estado civil e data de nascimento são 
todos conferidos e tratados pelo sistema e registrados em arquivos do tipo log, a fim de minimizar as exceções na execução do programa.

•	Utilização do sistema:

O processo se inicia com o cadastramento de uma Secretaria, a qual possui métodos para cadastrar os demais funcionários da clínica (inclusive
 outras secretarias) e pacientes de acordo com as especificações de cada classe (Médico deve possuir CRM, enfermeira deve possuir COREN e Paciente 
deve possuir convênio (que por sua vez possui crédito). A partir do cadastro de cada uma das classes citadas, cada uma delas pode executar as 
funcionalidades descritas acima. Esse cadastro é realizado a partir de uma leitura de arquivos txt como entrada não App.py, bem como as demais 
operações e suas respectivas saídas.

•	Tecnologias;
Python3, GitHub, Visual Studio Code e PyCharm;

•	Conclusões:
Pode-se observar com o desenvolvimento do ClinicaSys a importância da construção de soluções por meio da modularização, a fim de facilitar o 
entendimento e melhor organização da solução, e a relevância de estabelecer classes abstratas, estipulando com as demais classes que a herdavam 
espécies de “contratos”. Ademais, notou-se que as exceções que podem ser geradas com a execução do programa devem ser sempre tratadas para que ele 
possa prosseguir, independentemente se a entrada é esperada ou não, além de o trabalho com manipulação de arquivos ter sido proveitoso para  melhor
 entendimento na prática da escrita e leitura. Por fim , sobre um panorama geral do trabalho, avaliamos que foi um bom exercício para simularmos 
um problema e uma oportunidade de solução na vida real, em que do ponto de vista de um protótipo, o programa desenvolvido conseguiu atingir as 
expectativas e executar as funcionalidades exigidas para uma pequena clinica Hospitalar.
