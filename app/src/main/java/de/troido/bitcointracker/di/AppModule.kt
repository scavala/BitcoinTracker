package de.troido.bitcointracker.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.troido.domain.BitcoinRepository
import de.troido.domain.GetLatestPriceUseCase
import de.troido.network.repository.BitcoinRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBitcoinRepository(): BitcoinRepository {
        return BitcoinRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideGetLatestPriceUseCase(bitcoinRepository: BitcoinRepository) =
        GetLatestPriceUseCase(bitcoinRepository)


}