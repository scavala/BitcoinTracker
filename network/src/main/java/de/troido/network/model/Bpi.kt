package de.troido.network.model

import com.google.gson.annotations.SerializedName

data class Bpi(
    @SerializedName("USD") val USD: USD
)