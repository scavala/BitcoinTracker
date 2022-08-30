package de.troido.bitcointracker.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.troido.domain.BitcoinRepository
import de.troido.domain.GetLatestPriceUseCase

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGetLatestPriceUseCase(bitcoinRepository: BitcoinRepository) =
        GetLatestPriceUseCase(bitcoinRepository)

}