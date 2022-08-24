package de.troido.bitcointracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import de.troido.bitcointracker.R
import de.troido.bitcointracker.network.model.BitcoinData

class BitcoinPriceAdapter :
    ListAdapter<BitcoinData, BitcoinPriceAdapter.BitcoinViewHolder>(BitcoinDiffCallBack()) {
    class BitcoinViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        private val tvTime = itemView.findViewById<TextView>(R.id.tvTime)
        fun bind(data: BitcoinData) {
            tvPrice.text = data.bpi?.USD?.rate ?: "N/A"
            tvTime.text = data.time?.updatedISO
        }
    }

    private class BitcoinDiffCallBack : DiffUtil.ItemCallback<BitcoinData>() {
        override fun areItemsTheSame(oldItem: BitcoinData, newItem: BitcoinData): Boolean =
            oldItem.time == newItem.time

        override fun areContentsTheSame(oldItem: BitcoinData, newItem: BitcoinData): Boolean =
            oldItem.time == newItem.time
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BitcoinViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_price, parent, false)
        return BitcoinViewHolder(view)

    }

    override fun onBindViewHolder(holder: BitcoinViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}