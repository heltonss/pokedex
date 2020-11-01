package br.com.heltonsilveira.pokemonwstemplate.view.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.heltonsilveira.pokemonwstemplate.model.Pokemon
import br.com.heltonsilveira.pokemonwstemplate.model.RequestState
import br.com.heltonsilveira.pokemonwstemplate.repository.PokemonRepository


class DetailViewModel (val pokemonRepository: PokemonRepository) : ViewModel() {

    val pokemonResponse = MutableLiveData<RequestState<Pokemon?>>()

    fun getPokemon(number: String) {
        pokemonResponse.value = RequestState.Loading
        pokemonRepository.getPokemon (
            number,
            onComplete= {
                pokemonResponse.value = RequestState.Success(it)
            }, onError = {
                pokemonResponse.value = RequestState.Error(it)
            }
        )
    }
}