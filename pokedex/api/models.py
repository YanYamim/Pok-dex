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

class Pokemon_Ataque(models.Model):
    id_pokemon = models.ForeignKey('Pokemon', on_delete=models.CASCADE, related_name='pokemon_ataques')
    id_ataque = models.ForeignKey('Ataque', on_delete=models.CASCADE, related_name='ataque_pokemons')

    def __str__(self):
        return f'Id Pokémon: {self.id_pokemon} | Id Ataque:  {self.id_ataque}'

class Pokemon_Treinador(models.Model):
    id_pokemon = models.ForeignKey('Pokemon', on_delete=models.CASCADE, related_name='pokemon_treinadores')
    id_treinador = models.ForeignKey('Treinador', on_delete=models.CASCADE, related_name='treinador_pokemons')

    def __str__(self):
        return f'Id Pokémon: {self.id_pokemon} | Id Treinador:  {self.id_treinador}'

class Pokemon_Regiao(models.Model):
    id_pokemon = models.ForeignKey('Pokemon', on_delete=models.CASCADE, related_name='pokemon_regioes')
    id_regiao = models.ForeignKey('Regiao', on_delete=models.CASCADE, related_name='regiao_pokemons')

    def __str__(self):
        return f'Id Pokémon: {self.id_pokemon} | Id Região:  {self.id_regiao}'
