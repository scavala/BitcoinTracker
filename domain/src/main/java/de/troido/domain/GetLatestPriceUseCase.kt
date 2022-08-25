package de.troido.domain

class GetLatestPriceUseCase(
    private val bitcoinRepository: BitcoinRepository
) {
    suspend operator fun invoke(): Price {
        return bitcoinRepository.getPrice()
    }

}