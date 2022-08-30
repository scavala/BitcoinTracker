package de.troido.network.repository

import de.troido.domain.BitcoinRepository
import de.troido.domain.Price
import de.troido.network.BitcoinService
import javax.inject.Inject


class BitcoinRepositoryImpl @Inject constructor( private val service: BitcoinService) : BitcoinRepository {

    override suspend fun getPrice(): Price {
        val data = service.getBitcoinStats()
        return Price(data.time!!.updatedISO!!, data.bpi!!.USD!!.rate!!)
    }


}