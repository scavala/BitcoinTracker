package de.troido.bitcointracker.network.model

import com.google.gson.annotations.SerializedName

data class USD(
    @SerializedName("code") var code: String? = null,
    @SerializedName("symbol") var symbol: String? = null,
    @SerializedName("rate") var rate: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("rate_float") var rateFloat: Double? = null
)