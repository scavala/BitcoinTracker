package de.troido.bitcointracker.network.model

import com.google.gson.annotations.SerializedName

data class Time(
    @SerializedName("updatedISO") var updatedISO: String? = null
)