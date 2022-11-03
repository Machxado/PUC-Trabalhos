class Erros(Exception):

    def __init__(self, valor):
        self._valor = valor

    def __str__(self):
        return "Dado não válido"


## Tratar os erros de CPF, COREN, CRM, estado civil (solteiro/casado) e data de nascimento (dia/mes/ano).

class TratamentoCPF1(Erros):

    def __init__(self, CPF):
        self._CPF = CPF

    def __str__(self):
        escreve = self._CPF + " verifique se o cpf apresenta 11 caracteres e se todos são são numeros inteiros"
        return escreve

class TratamentoCPF2(Erros):

    def __init__(self, CPF):
        self._CPF = CPF

    def __str__(self):
        escreve=self._CPF + " já está cadastrado no sistema"
        return escreve


class TratamentoCOREN(Erros):

    def __init__(self, COREN):
        self._COREN = COREN

    def __str__(self):
        escreve=self._COREN + "COREN já está cadastrado"
        return escreve


class TratamentoEstadoCivil(Erros):

    def __init__(self, EstadoCivil):
        self._EstadoCivil = EstadoCivil

    def __str__(self):
       escreve=self._EstadoCivil + " não é um estado civil válido"
       return escreve


class TratamentoCRM(Erros):

    def __init__(self, CRM):
        self._CRM = CRM

    def __str__(self):
        escreve=self._CRM + "CRM já está cadastrado"
        return escreve


class TratamentoDataNasc(Erros):

    def __init__(self, DataNasc):
        self._DataNasc = DataNasc

    def __str__(self):
        escreve =self._DataNasc + " Data de nascimento inválida"
        return escreve


class TratamentoConvenio(Erros):

    def __init__(self, Convenio):
        self._Convenio = Convenio

    def __str__(self):
        escreve=self._Convenio + " Não é um convênio válido!"
        return escreve