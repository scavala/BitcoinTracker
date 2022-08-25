package de.troido.bitcointracker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.troido.domain.GetLatestPriceUseCase
import de.troido.domain.Price
import de.troido.network.model.BitcoinData
import de.troido.network.repository.BitcoinRepositoryImpl
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class BitcoinViewModel() : ViewModel() {


    val bitcoinLastPrices = MutableLiveData<LinkedList<Price>>()
    private val executor = Executors.newSingleThreadScheduledExecutor()
    private val repo: GetLatestPriceUseCase

    init {
        repo = GetLatestPriceUseCase(BitcoinRepositoryImpl())
    }

    fun getPrices() {
        executor.scheduleAtFixedRate({
            viewModelScope.launch {
                getLatestPrices()
            }
        }, 0, 15, TimeUnit.SECONDS)
    }

    private suspend fun getLatestPrices() {
        val bitcoinLatestPrice = repo.invoke()
        val prices = bitcoinLastPrices.value
        if (prices == null) {
            bitcoinLastPrices.value = LinkedList<Price>(listOf(bitcoinLatestPrice))
        } else {
            val newPriceList = bitcoinLastPrices.value!!
            newPriceList.push(bitcoinLatestPrice)
            if (newPriceList.size > 50) newPriceList.removeLast()
            bitcoinLastPrices.value = LinkedList<Price>(newPriceList)

        }
    }

}