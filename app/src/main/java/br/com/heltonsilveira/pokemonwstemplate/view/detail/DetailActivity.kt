package br.com.heltonsilveira.pokemonwstemplate.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import br.com.heltonsilveira.pokemonwstemplate.R
import br.com.heltonsilveira.pokemonwstemplate.model.RequestState
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.include_loading.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    val detailViewModel: DetailViewModel by viewModel()
    val picasso: Picasso by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detailViewModel.getPokemon(intent.getStringExtra("POKEMON_NUMBER"))

        detailViewModel.pokemonResponse.observe(this, Observer {
            when(it) {
                is RequestState.Success -> {
                    picasso.load("https://pokedexdx.herokuapp.com${it.data?.urlImagem}").into(ivPokemon)
                    tvPokemonName.text = "${it.data?.numero} ${it.data?.nome}"
                    containerLoading.visibility = View.GONE
                }

                is RequestState.Loading -> {
                        containerLoading.visibility = View.VISIBLE
                }

                is RequestState.Error -> {
                    containerLoading.visibility = View.GONE
                    Toast.makeText(this, it.throwable.message, Toast.LENGTH_LONG).show()

                }
            }

        })

    }
}
