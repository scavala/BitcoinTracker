package de.troido.bitcointracker

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import de.troido.bitcointracker.adapter.BitcoinPriceAdapter
import de.troido.bitcointracker.databinding.ActivityMainBinding
import de.troido.bitcointracker.viewmodel.BitcoinViewModel
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: BitcoinViewModel by viewModels()
    private val executor = Executors.newSingleThreadScheduledExecutor()

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
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        executor.scheduleAtFixedRate({ viewModel.getPrices() }, 0, 15, TimeUnit.SECONDS)

    }

    override fun onStop() {
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
        super.onStop()
    }
}