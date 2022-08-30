package de.troido.bitcointracker.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.troido.domain.BitcoinRepository
import de.troido.network.BitcoinService
import de.troido.network.repository.BitcoinRepositoryImpl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    @Provides
    @Singleton
    fun provideBitcoinService(): BitcoinService {
        val httpClient = OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)

        val retrofit =
            Retrofit.Builder().baseUrl("https://api.coindesk.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build()).build()
        return retrofit.create(BitcoinService::class.java)
    }

    @Provides
    fun provideBitcoinRepository(service: BitcoinService): BitcoinRepository {
        return BitcoinRepositoryImpl(service)
    }

}