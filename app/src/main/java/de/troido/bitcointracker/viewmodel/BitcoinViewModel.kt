package de.troido.bitcointracker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.troido.domain.GetLatestPriceUseCase
import de.troido.domain.Price
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class BitcoinViewModel @Inject constructor(private val getLatestPriceUseCase: GetLatestPriceUseCase) :
    ViewModel() {


    val bitcoinLastPrices = MutableLiveData<LinkedList<Price>>()
    private val executor = Executors.newSingleThreadScheduledExecutor()


    fun getPrices() {
        executor.scheduleAtFixedRate({
            viewModelScope.launch {
                getLatestPrices()
            }
        }, 0, 15, TimeUnit.SECONDS)
    }

    private suspend fun getLatestPrices() {
        val bitcoinLatestPrice = getLatestPriceUseCase.invoke()
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