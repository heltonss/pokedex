package br.com.heltonsilveira.pokemonwstemplate.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    @SerializedName("number") val numero: String,
    @SerializedName("name") val nome: String,
    @SerializedName("imageURL") val urlImagem: String,
    var ps: Int,
    var attack: Int,
    var defense: Int,
    var velocity: Int
) : Parcelable