package com.ambrella.criptoapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ambrella.criptoapp.R
import com.ambrella.criptoapp.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_crp.view.*

class CoinInfoAdapter: RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {


    var coinInfoList:List<CoinPriceInfo> = listOf()
        set(value)
        {
            field=value
            notifyDataSetChanged()
        }

    var onCoinClicLisener:OnCoinClicLisener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        // val view: View! = LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info,parent,false)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_crp,parent,false)
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin= coinInfoList[position]
        holder.tvSymvols.text=coin.fromsymbol + " | "+coin.tosymbol
        holder.tvPrice.text = coin.price.toString()
        holder.tvlastupdate.text=" Время последнего обновления "+coin.getFormattedTime()
        Picasso.get().load(coin.getFullImageUrl()).into(holder.ivLogoCoin)
        holder.itemView.setOnClickListener {
            onCoinClicLisener?.onCoinClick(coin)
        }
    }

    override fun getItemCount()= coinInfoList.size
    inner class CoinInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val ivLogoCoin=itemView.ivLogoCoin
        val tvSymvols=itemView.tvSymvols
        val tvPrice=itemView.tvPrice
        val tvlastupdate=itemView.tvlastupdate
    }
    interface OnCoinClicLisener
    {
        fun onCoinClick(coinPriceInfo: CoinPriceInfo)
    }
}