package de.troido.network

import de.troido.network.model.BitcoinData
import retrofit2.http.GET

interface BitcoinService {

    @GET("v1/bpi/currentprice.json")
    suspend fun getBitcoinStats(): BitcoinData

}