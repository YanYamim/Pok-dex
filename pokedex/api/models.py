from django.db import models

class Pokemon(models.Model):
    id_pokemon = models.AutoField(primary_key=True)
    nome_pokemon = models.CharField(max_length=20, default= '', null=False)
    tipo_pokemon = models.CharField(max_length=8, default='')
    tamanho = models.DecimalField(max_digits=5, decimal_places=2)
    peso = models.DecimalField(max_digits=5, decimal_places=2)
    bioma = models.CharField(max_length=20, default='')

    def __str__(self):
        return f'Nome: {self.nome_pokemon} | Tipo: {self.tipo_pokemon}'
    
class Treinador(models.Model):
    id_treinador = models.AutoField(primary_key=True)
    nome_treinador = models.CharField(max_length=50)
    cidade = models.CharField(max_length=50)

    def __str__(self):
        return f'Nome: {self.nome_treinador} | Cidade: {self.cidade}'
    
class Ataque(models.Model):
    id_ataque = models.AutoField(primary_key=True)
    nome_ataque = models.CharField(max_length=50, null=False)
    tipo_ataque = models.CharField(max_length=20)
    dano = models.IntegerField(default=0)
    precisao = models.IntegerField(default=0)
    pp = models.IntegerField(default=0)

    def __str__(self):
        return f'Nome: {self.nome_ataque} | Cidade: {self.dano}'
    
class Regiao(models.Model):
    id_regiao = models.AutoField(primary_key=True)
    nome_regiao = models.CharField(max_length=50, null=False)

    def __str__(self):
        return f'Nome: {self.nome_regiao}'