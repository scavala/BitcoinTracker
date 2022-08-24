package de.troido.network.model

import com.google.gson.annotations.SerializedName

data class BitcoinData(
    @SerializedName("time") var time: Time? = Time(),
    @SerializedName("chartName") var chartName: String? = null,
    @SerializedName("bpi") var bpi: Bpi? = Bpi()
)