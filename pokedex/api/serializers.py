from rest_framework import serializers
from .models import Pokemon, Treinador, Ataque, Regiao

class PokemonSerializer(serializers.ModelSerializer):
    class Meta:
        model = Pokemon
        fields = '__all__'

class TreinadorSerializer(serializers.ModelSerializer):
    class Meta:
        model = Treinador
        fields = '__all__'

class AtaqueSerializer(serializers.ModelSerializer):
    class Meta:
        model = Ataque
        fields = '__all__'

class RegiaoSerializer(serializers.ModelSerializer):
    class Meta:
        model = Regiao
        fields = '__all__'