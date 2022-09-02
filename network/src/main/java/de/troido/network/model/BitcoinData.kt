package de.troido.network.model

import com.google.gson.annotations.SerializedName

data class BitcoinData(
    @SerializedName("time") val time: Time,
    @SerializedName("chartName") val chartName: String,
    @SerializedName("bpi") val bpi: Bpi,
)