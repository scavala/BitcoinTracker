package de.troido.bitcointracker.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://api.coindesk.com/"

class Network {

    private val service: BitcoinService

    init {
        val httpClient = OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)

        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build()).build()
        service = retrofit.create(BitcoinService::class.java)
    }

    fun getService(): BitcoinService = service
}