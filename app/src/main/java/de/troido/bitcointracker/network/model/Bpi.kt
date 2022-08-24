package de.troido.bitcointracker.network.model

import com.google.gson.annotations.SerializedName

data class Bpi(
    @SerializedName("USD") var USD: USD? = USD()
)