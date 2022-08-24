package de.troido.network.repository

import de.troido.network.Network

class BitcoinRepository {

    suspend fun getLast() = Network().getService().getBitcoinStats()

}