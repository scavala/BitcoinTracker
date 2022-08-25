package de.troido.network.repository

import de.troido.domain.BitcoinRepository
import de.troido.domain.Price
import de.troido.network.Network


class BitcoinRepositoryImpl : BitcoinRepository {

    override suspend fun getPrice(): Price {
        val data = Network().getService().getBitcoinStats()
        return Price(data.time?.updatedISO, data.bpi?.USD?.rate)
    }


}