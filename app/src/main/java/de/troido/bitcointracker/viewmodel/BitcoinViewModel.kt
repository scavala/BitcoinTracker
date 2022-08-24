package de.troido.bitcointracker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.troido.network.model.BitcoinData
import de.troido.network.repository.BitcoinRepository
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class BitcoinViewModel : ViewModel() {


    val bitcoinLastPrices = MutableLiveData<LinkedList<BitcoinData>>()
    private val executor = Executors.newSingleThreadScheduledExecutor()
    private val repo: BitcoinRepository

    init {
        repo = BitcoinRepository()
    }

    fun getPrices() {
        executor.scheduleAtFixedRate({
            viewModelScope.launch {
                getLatestPrices()
            }
        }, 0, 15, TimeUnit.SECONDS)
    }

    private suspend fun getLatestPrices() {
        val bitcoinLatestPrice = repo.getLast()
        val prices = bitcoinLastPrices.value
        if (prices == null) {
            bitcoinLastPrices.value = LinkedList<BitcoinData>(listOf(bitcoinLatestPrice))
        } else {
            val newPriceList = bitcoinLastPrices.value!!
            newPriceList.push(bitcoinLatestPrice)
            if (newPriceList.size > 50) newPriceList.removeLast()
            bitcoinLastPrices.value = LinkedList<BitcoinData>(newPriceList)

        }
    }

}