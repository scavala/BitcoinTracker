package de.troido.domain

import de.troido.network.repository.BitcoinRepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


class GetLatestPriceUseCaseTest {

    private var repositoryMock: BitcoinRepository = mock(BitcoinRepository::class.java)
    private lateinit var tested: GetLatestPriceUseCase

    @Before
    fun setUp() {
        tested = GetLatestPriceUseCase(repositoryMock)
    }

    @Test
    fun `invoke should be valid`() = runTest {
        val expected = Price("dasda", "2")
        `when`(repositoryMock.getPrice()).thenReturn(expected)

        val actual = tested.invoke()

        verify(repositoryMock, atMost(1)).getPrice()
        assertEquals(expected, actual)


    }
}