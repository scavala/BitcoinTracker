package de.troido.bitcointracker

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import de.troido.bitcointracker.adapter.BitcoinPriceAdapter
import de.troido.bitcointracker.databinding.ActivityMainBinding
import de.troido.bitcointracker.viewmodel.BitcoinViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: BitcoinViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)

        val recyclerView = binding.rvPrices
        val priceAdapter = BitcoinPriceAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = priceAdapter
        }

        viewModel.bitcoinLastPrices.observe(this) { priceList ->
            priceAdapter.submitList(priceList?.toMutableList())

        }
        viewModel.getPrices()
        setContentView(binding.root)
    }


}