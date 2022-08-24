package de.troido.bitcointracker.network

import de.troido.bitcointracker.network.model.BitcoinData
import retrofit2.http.GET

interface BitcoinService {

    @GET("v1/bpi/currentprice.json")
    suspend fun getBitcoinStats(): BitcoinData

}