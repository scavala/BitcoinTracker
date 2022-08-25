package de.troido.domain

interface BitcoinRepository {
    suspend fun getPrice():Price
}