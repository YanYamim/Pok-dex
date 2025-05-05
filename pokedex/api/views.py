from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status

from .models import Pokemon
from .serializers import PokemonSerializer

@api_view(['GET'])
def listar_pokemons(request):
    if request.method == 'GET':
        pokemons = Pokemon.objects.all()

        serializer = PokemonSerializer(pokemons, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)
    
    return Response(status=status.HTTP_404_NOT_FOUND)

@api_view(['POST'])
def registrar_pokemon(request):
    if request.method == 'POST':
        novo_pokemon = request.data

        serializer = PokemonSerializer(data=novo_pokemon)

        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
    
    return Response(status=status.HTTP_400_BAD_REQUEST)

@api_view(['PUT'])
def editar_pokemon(request):
    try:
        id_pokemon = request.data.get('id_pokemon') 
        pokemon = Pokemon.objects.get(id_pokemon=id_pokemon)
    except Pokemon.DoesNotExist:
        return Response({'error': 'Pokemon não encontrado.'}, status=status.HTTP_404_NOT_FOUND)       

    serializer = PokemonSerializer(pokemon, data=editar_pokemon)

    if serializer.is_valid():
        serializer.save()
        return Response(serializer.data, status=status.HTTP_200_OK)
    
    return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)