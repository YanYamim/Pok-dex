from django.shortcuts import render
from django.http import HttpResponse, JsonResponse

from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status

from .models import Pokemon
from .serializers import PokemonSerializer

import json


@api_view(['GET'])
def listar_pokemons(request):
    if request.method == 'GET':
        pokemons = Pokemon.objects.all()

        serializer = PokemonSerializer(pokemons, many=True)
        return Response(serializer.data)
    
    return Response(status=status.HTTP_404_NOT_FOUND)