package de.troido.network.repository

import de.troido.domain.Price
import de.troido.network.BitcoinService
import de.troido.network.model.BitcoinData
import de.troido.network.model.Bpi
import de.troido.network.model.Time
import de.troido.network.model.USD
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class BitcoinRepositoryImplTest {

    private lateinit var tested: BitcoinRepositoryImpl

    private var serviceMock: BitcoinService = Mockito.mock(BitcoinService::class.java)


    @Before
    fun setUp() {
        tested = BitcoinRepositoryImpl(serviceMock)
    }

    @Test
    fun `repository should be valid`() = runTest {

        val expected = BitcoinData(Time("21"), "BTC", Bpi(USD("22222")))
        Mockito.`when`(serviceMock.getBitcoinStats()).thenReturn(expected)

        val actual = tested.getPrice()

        Mockito.verify(serviceMock, Mockito.atMost(1)).getBitcoinStats()
        assertEquals(Price(expected.time.updatedISO, expected.bpi.USD.rate), actual)


    }
}