from django.urls import path
from .views import listar_pokemons, registrar_pokemon, editar_pokemon

urlpatterns = [
    path('pokemons/', listar_pokemons, name='listar_pokemons'),
    path('pokemons/registrar', registrar_pokemon, name='registrar_pokemon'),
    path('pokemons/editar', editar_pokemon, name='editar_pokemon')
]