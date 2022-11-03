from abc import ABCMeta, abstractmethod
from datetime import *

from ClinicaSys import TratamentoErros as erro


class Pessoa(metaclass=ABCMeta):
    '''
        Classe Pessoa, classe base para toda construção, todos os objeto são pessoas que podem cadastrar dados,
        ou obter dados de alguém(exceto pacientes, que n possuem esses métodos implementados)
    '''

    def __init__(self, nome, cpf, dataNasc, EstCivil):
        '''
        Contrutor da classe pessoa que cria uma pessoa caso todos os campos estejam preenchidos corretamente
        :param nome:
        :param cpf:
        :param dataNasc:
        :param EstCivil:
        :returns um Objeto Pessoa
        '''
        self.__nome = nome
        if self.__verificarCPF(cpf):
            self.__cpf = cpf
        if self.__verificarData(dataNasc):
            self.__dataNasc = dataNasc
        if self.__verificaEstCivil(EstCivil):
            self.__EstCivil = EstCivil

    @property
    def nome(self):  # getter de nome
        return self.__nome

    @nome.setter
    def nome(self, nome):  # setter de nome
        self.__nome = nome

    @property
    def cpf(self):  # getter de cpf
        return self.__cpf

    @cpf.setter
    def cpf(self, novocpf=str):  # setter de cpf
        if (self.__verificarCPF(novocpf)):
            self.__cpf = novocpf

    @property
    def dataNasc(self):  # getter de data de nascimento
        return self.__dataNasc

    @dataNasc.setter
    def dataNasc(self, novadata):  # setter de data de nascimento
        if self.__verificarData(novadata):
            self.__dataNasc = novadata

    @property
    def EstCivil(self):  # getter de Estado civil
        return self.__EstCivil

    @EstCivil.setter
    def EstCivil(self, novoEst):  # setter de Estado civil
        if self.__verificaEstCivil(novoEst):
            self.__EstCivil = novoEst

    @abstractmethod
    def _CadastrarDados(self, ToWrite: str, arquivo: str):
        pass  # realiza o Contrato de obrogatoriedade de implementação nas classes filhas

    @abstractmethod
    def _ObterDados(self,pessoa):
        pass  # realiza o Contrato de obrogatoriedade de implementação nas classes filhas

    @staticmethod
    def __verificaEstCivil(VerificaEstCivil):
        '''
        Verificador de estado civil
        :param VerificaEstCivil:
        :return: se o estado civil está dentro dos conformes
        '''
        try:
            if VerificaEstCivil == 'Solteiro' or VerificaEstCivil == 'Casado' or VerificaEstCivil == 'Solteira' or VerificaEstCivil == 'Casada':
                return True
            else:
                raise erro.TratamentoEstadoCivil(VerificaEstCivil)
        except erro.TratamentoEstadoCivil as e:
            with open('LOG.txt','a')as file:
                escreve=str(e)+'\n'
                file.write(escreve)
            print(e)
    @staticmethod
    def __verificarData(dataNasc):
        '''
        Verificador de Data
        :param dataNasc:
        :return: se a data esta dentro dos padrões true or false
        '''

        data = datetime.strptime(dataNasc, '%d/%m/%Y').date()  ##tratar erro
        try:
            if data > date.today():
                raise erro.TratamentoDataNasc(dataNasc)
            else:
                return True
        except erro.TratamentoDataNasc as e:
            with open('LOG.txt', 'a') as file:
                escreve = str(e) + '\n'
                file.write(escreve)
            print(e)

    @staticmethod
    def __verificarCPF(CPF):
        '''
        Verificador de CPF
        :param CPF:
        :return: retorna true ou false se o CPF está correto
        '''
        try:
            if (CPF.isdigit() and len(CPF) == 11):  # Tratar erro!!
                return True
            else:
                raise erro.TratamentoCPF1(CPF)
        except erro.TratamentoCPF1 as e:
            with open('LOG.txt', 'a') as file:
                escreve = str(e) + '\n'
                file.write(escreve)
            print(e)

    def __str__(self):
        '''
        METODO TO STRING
        :return: as propriedades descritas da classe
        '''
        return str(self.__nome + ', CPF: ' + self.__cpf + ', data de Nascimento: ' + self.__dataNasc + ' Estado Civil: ' + self.__EstCivil)

class Funcionarios(Pessoa):
    '''
    Classe Funcionário Serve pra distinguir pacientes de funcionários, auxilia no momento de CadastrarPacientes
    ou Cadastrar Funcionarios agindo como um filtro e implementando um CadastrarDados e ObterDados Padrao à
    todas as classes de funcionários
    :param (mesmos parâmetros de Pessoa)
    '''
    _CORENLIST = []  # lista de corens ja registrados no funcionarios.dat
    _CRMLIST = []  # lista de CRMS ja registrados no funcionarios.dat

    def __init__(self, nome, cpf, dataNasc, EstCivil):
        '''
        Construtor de Funcionários (mesmo de Pessoa)
        :param nome:
        :param cpf:
        :param dataNasc:
        :param EstCivil:
        '''
        Pessoa.__init__(self, nome, cpf, dataNasc, EstCivil)

    def _CadastrarDados(self, ToWrite: str, arquivo: str):
        '''
        Método que cadastra dados em um arquivo que pode ser passado como parâmetro, juntamento com o que se
        deseja escrever
        :param ToWrite:
        :param arquivo:
        :return: append o que você escreveu no arquivo designado
        '''
        with open(arquivo, 'a') as file:
            file.write(ToWrite)

    def _ObterDados(self, pessoa):
        '''
        Método que obtem os dados de determinado objeto(Pessoa ou Funcionario), através do método
        __str__() do objeto passado
        :param pessoa:
        :return: string das infos do objeto
        '''
        return pessoa.__str__()

    def Clearlists(self):
        '''
        Método que limpa as listas caso necessário
        '''
        self._CRMLIST.clear()
        self._CORENLIST.clear()

    def __str__(self):
        return Pessoa.__str__(self)

class Secretaria(Funcionarios):
    '''
    Classe Secretária Possui os métodos requisitados através das Funções disponibilizadas em sua
    super(Funcionarios)
    '''

    def __init__(self, nome, cpf, dataNasc, EstCivil):
        '''
        Construtor da classe (Igual Pessoa)
        :param nome:
        :param cpf:
        :param dataNasc:
        :param EstCivil:
        '''
        Pessoa.__init__(self, nome, cpf, dataNasc, EstCivil)

    def cadastrarFuncionario(self, Funcionario):
        """
        Método para cadastrar Funcionários: recebe obrigatóriamente um objeto Funcionário e obtem os seus dados
        através do método obterdados(Funcionário), que é o parâmetro do que será escrito com o método CadastrarDados
        A secretária so pode manipular o arquivo Funcionários.dat, então ele já está fixado como parâmetro de arquivo
        :param Funcionario:
        :return: Cadastra o Funcionário recebido em funcionários.dat
        """
        escreve = self._ObterDados(Funcionario)+'\n'
        self._CadastrarDados(escreve, 'Funcionarios.dat')


    def __str__(self):
        '''
        Metodo de String
        :return: Suas informações em string (Mesmo String de Pessoa)
        '''
        return Pessoa.__str__(self)

class Convenio():
    '''
    Classe convenio a classe convenio possui 4 tipos de conveios possíveis:
    os pedidos pelo professor e o tachimed porque é de bento e bento é top
    também possui a propriedade crédito que determina se o paciente derá atendido ou não
    '''

    def __init__(self, name):
        '''
        Construtor da classe Convenio
        :param nome:
        :param credito:
        :returns Cria um Objeto Convenio com atributos de Tipo do Convenio e quantidade de créditos do Paciente
        '''
        if self.verificaConvenio(name):
            self.__nome = name

    @property  # getter de nome do convenio
    def nome(self):
        return self.__nome

    @nome.setter  # setter do tipo de convenio
    def nome(self, nome):
        if self.verificaConvenio(nome):
            self.__nome = nome


    @classmethod
    def verificaConvenio(self, nome):
        '''
        Verificador de Convenio
        :param nome:
        :return: se o nome do Convenio corresponde a um dos 4 Convenios permitidos
        '''
        try:
            if nome == 'SUS' or nome == 'IPE' or nome == 'Unimed' or nome == 'Tachimed':
                return True
            else:
                raise erro.TratamentoConvenio(nome)
        except erro.TratamentoConvenio as e:
            with open('LOG.txt', 'a') as file:
                escreve = str(e) + '\n'
                file.write(escreve)
            print(e)

class Paciente(Pessoa):
    '''
    Classe Paciente é uma subclasse de Pessoa que seus metodos cadastrardados e obter dados não fazem nada,
    pois um paciente não pode ter esse poder
    '''

    def __init__(self, nome, cpf, dataNasc, EstCivil, convenio, credito=int):
        '''
        Contrutor da classe Paciente(igual pessoa), porém pede 2 parametros a mais, sendo esses: convenio do paciente
        e credito do paciente, possui também atributos de Liberado? e diagnostico, que representam se o paciente
        está ou não liberado e qual seu diagnóstico, respectivamente.
        :param nome:
        :param cpf:
        :param dataNasc:
        :param EstCivil:
        :param convenio:
        :param credito:
        '''
        Pessoa.__init__(self, nome, cpf, dataNasc, EstCivil)
        self.__Convenio = Convenio(convenio)
        self.__creditos = credito
        self.__liberado = False
        self.__diagnostico = None

    def __isub__(self,valor = int):
        self.__creditos -= valor

    def _ObterDados(self, pessoa): pass  # metodo de obter dados sem função

    def _CadastrarDados(self, ToWrite: str, arquivo: str): pass  # metodo de cadastrar dados sem função

    @property
    def Convenio(self):
        return self.__Convenio.nome
    @Convenio.setter
    def Convenio(self,novoConv):
        if self.__Convenio.verificaConvenio(novoConv):
            self.__Convenio.nome = novoConv

    @property
    def creditos(self):  # getter dos creditos
        return self.__creditos

    @creditos.setter  # setter dos creditos
    def creditos(self, novocredito):
        self.__creditos = novocredito

    @property
    def liberado(self):  # getter da liberação
        return self.__liberado

    @liberado.setter  # setter da liberação
    def liberado(self, liberacao: bool):
        self.__liberado = liberacao

    @property  # getter do diagnostico
    def diagnostico(self):
        return self.__diagnostico

    @diagnostico.setter  # setter do diagnostico
    def diagnostico(self, Diagnostico: str):
        self.__diagnostico = Diagnostico

    @diagnostico.deleter  # deleter do diagnostico para rediagnosticação se necessário
    def diagnostico(self):
        self.__diagnostico = None


    def __str__(self):
        '''
        Método ToString do paciente
        :return: String das suas informações(igual Pessoa)
        '''
        return Pessoa.__str__(self)

class Medico(Funcionarios):
    '''
    Classe Medico é uma subclasse de Funcionarios e de Pessoa,
     além de possuir todos os métodos requisitados pelo professor
    '''

    def __init__(self, nome, cpf, dataNasc, EstCivil, CRM):
        '''
        Construtor da classe médico recebe as mesmas informações de pessoa, porém um atributo a mais sobre seu CRM
        em seu construtor faz a verificação na lista de CRMS já existentes.
        :param nome:
        :param cpf:
        :param dataNasc:
        :param EstCivil:
        :param CRM:
        '''
        Pessoa.__init__(self, nome, cpf, dataNasc, EstCivil)
        self.__verificaCRM(CRM)
        self.__CRM = CRM
        Funcionarios._CRMLIST.append(CRM)

    @property  # getter de CRM
    def CRM(self):
        return self.__CRM

    @CRM.setter  # setter de CRM
    def CRM(self, CRM):
        if self.__verificaCRM(CRM):
            self.__CRM = CRM

    def __verificaCRM(self, CRM):
        '''
        Verificador de CRM, percorre a lista de CRMS e retorna se esse CRM já está cadastrado ou não
        :param CRM:
        :return: True ou False sobre a já ocorrência do CRM e garante que o mesmo é composto apenas por números
        '''
        try:

            if CRM.isdigit():
                for i in self._CRMLIST:
                    if i == CRM:
                        raise erro.TratamentoCPF2
                    else:
                        return True
        except erro.TratamentoCPF2 as e:
            with open('LOG.txt', 'a') as file:
                escreve = str(e) + '\n'
                file.write(escreve)
            print(e)

    def internar(self, Paciente: Paciente):
        '''
        interna o paciente e verifica se o Paciente possui créditos para receber o atendimento,
        caso tenha decrementa seus Creditos se não for um Cliente SUS
        :param Paciente:
        :return: void
        '''
        if int(Paciente.creditos) >= 0:
            if Paciente.Convenio != 'SUS':
                Paciente -= 500
        else:
            print('Cliente não possui crédito suficiente')

    def diagnosticar(self, paciente=Paciente, Diagnostico=str):
        '''
        Essa é complexa! O método por começo verifica se o paciente possui crédito para ser diagnosticado ou não,
        e também verifica se o Paciente é cliente SUS ou não para realizar a decrementação dos créditos,
        após, o método procura o paciente passado como parametro no arquivo de Pacientes.dat,
        ao encontrar o paciente, ele obtem seus dados, acrescenta o diagnóstico e salva-os em uma variável,
        Então ele salva TODO O ARQUIVO em um array, e procura no array pela linha que corresponde ao paciente em questão,
        quando acha o paciente no array o método sobrescreve as informações acrescentando o diagnóstico armazenado na varável anterior,
        depois disso o método deleta tudo que está escrito no pacientes.dat e reescreve com o array que armazena tudo
        + o paciente diagnosticado. Sim, eu demorei muito pra pensar nisso #valeuLeoMonitorPelaAjuda!
        :param Paciente:
        :param Diagnostico:
        :return:
        '''

        if int(paciente.creditos) >= 0:
            paciente.diagnostico = Diagnostico
            if paciente.Convenio != 'SUS':

                paciente.__isub__(150)

        else:
            print('Cliente não Possui Credito Suficiente')


        with open('pacientes.dat', 'r+') as file:
            array=[]
            for i in file:
                j = i.split(',')
                if len(j)>2:
                    if (j[1] == ' CPF: ' + str(paciente.cpf)):
                        escreve = i[0:(len(i)-2)] + ', Diagnóstico: ' + Diagnostico + '\n'
                        array = file.readlines()
                        for i in range(len(array)):
                            if str(paciente.cpf) in array[i]:
                                array[i] = escreve
                                file.seek(0)
                                file.truncate()
                                file.writelines(array)

    def liberar(self, Paciente: Paciente):
        '''
        O método verifica se o paciente ja foi diagnosticado, caso isso seja verdadeiro libera o paciente
        :param Paciente:
        :return:
        '''
        if Paciente.diagnostico is not None:
            Paciente.liberado = True
        else:
            print('Você não pode liberar um Paciente não diagnotsticado')

    def __str__(self):
        '''
        Metodo Tostring da classe médico que retorna as informações da sua pessoa + o seu CRM
        :return:
        '''
        return Pessoa.__str__(self) + ', CRM: ' + str(self.__CRM)

class Enfermeira(Funcionarios):
    '''
    Classe Enfermeira é uma subclasse de Funcionario e Pessoa, e também implementa os métodos requisitados pelo
    professor
    '''

    def __init__(self, nome, cpf, dataNasc, EstCivil, COREN):
        '''
        Construtor da classe enfermeira Igual o de Pessoa, e recebe também o COREN da enfermeira
        :param nome:
        :param cpf:
        :param dataNasc:
        :param EstCivil:
        :param COREN:
        '''
        Pessoa.__init__(self, nome, cpf, dataNasc, EstCivil)
        if self.__verificaCoren(COREN):
            self.__COREN = COREN
            Funcionarios._CORENLIST.append(COREN)

    @property  # getter do COREN
    def COREN(self):
        return self.__COREN

    @COREN.setter  # setter do COREN Tratar Erro!!
    def COREN(self, COREN):
        if self.__verificaCoren(COREN):
            self.__COREN = COREN

    def cadastrarPacientes(self, Paciente):
        '''
        O método Cadastra o paciente no arquivo paciente.dat, obtendo suas informações através do método
        obterdados e também acrescenta a informação sobre sua quantidade de créditos e escreve no arquivo
        pacienteds.dat (Parâmetro fixo para esse método)
        :param Paciente:
        :return: escreve no arquivo
        '''
        write = self._ObterDados(Paciente) + ', Créditos: '+ str(Paciente.creditos)+'\n'
        self._CadastrarDados(write, 'Pacientes.dat')

    def gerarRelatorio(self, Medico: Medico, Paciente: Paciente):
        '''
        Este Método gera o relatório com as informações solicitadas através do método Cadastrar dados,
        Primeiramente o método verifica se o paciente ja foi liberado, para n gerar relatório de um paciente
        que ainda não foi liberado(lembrando que para ser liberado requer ser diagnosticado),
        para cada Paciente ele extrai seu CPF e passa como argumento na parte de arquivo do método de cadastro
        :param Medico:
        :param Paciente:
        :return:
        '''
        if Paciente.liberado:
            write = 'RELATÓRIO HOSPITAL SÃO LUCAS\n=======================================================\nDia ' + str(
                date.today()) + '\nRelatamos que o Paciente: ' + self._ObterDados(
                Paciente) + ',\nfoi atendido pelo médico: ' + self._ObterDados(
                Medico) + '\nPossui o Convênio: ' + str(
                Paciente.Convenio) + ' e saldo de R$' + str(
                Paciente.creditos) + ', Diagnóstico: ' + Paciente.diagnostico
            CPF=Paciente.cpf
            Arquivo = 'Rel_' + CPF + '.txt'
            self._CadastrarDados(write, Arquivo)
        else:
            print('não é possível gerar relatório de um paciente que não foi liberado!')  # Tratar Erro\!!

    def __str__(self):
        '''
        To string da classe enfermeira igual pessoa, mas acrescenta o atributo COREN
        :return:
        '''
        return Pessoa.__str__(self) + ', COREN: ' + str(self.__COREN)

    def __verificaCoren(self, COREN):
        '''
        verifica se o COREN possui apenas digitos para realizar uma verificação de padrao
        e também analisa se já está registrado no sistema
        :param COREN:
        :return: True False
        '''
        try:
            if COREN.isdigit():
                return True
            else:
                raise erro.TratamentoCOREN(COREN)
        except erro.TratamentoCOREN as e:
            with open('LOG.txt', 'a') as file:
                escreve = str(e) + '\n'
                file.write(escreve)
            print(e)

        try:
            for i in Funcionarios._CORENLIST:
                if i == COREN:
                    raise erro.TratamentoCOREN(COREN)
                else:
                    return True
        except erro.TratamentoCOREN as e:
            with open('LOG.txt','a')as file:
                escreve=str(e)+'\n'
                file.write(escreve)
            print(e)




