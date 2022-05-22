package com.example.testapplicationwithrxjava2.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapplicationwithrxjava2.databinding.CryptomainViewHolderBinding
import com.example.testapplicationwithrxjava2.models.CurrencyResponseItem

class CryptoMainAdapter(): ListAdapter<CurrencyResponseItem, CryptoMainAdapter.ViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = CryptomainViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }



    class ViewHolder(private val itemBinding: CryptomainViewHolderBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(component: CurrencyResponseItem){
            itemBinding.tvRank.text = component.rank
            Glide.with(itemView).load(component.logo_url).into(itemBinding.ivCrypto)
            itemBinding.tvName.text = component.name
            itemBinding.tvPrice.text = component.price
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CurrencyResponseItem>() {
        override fun areItemsTheSame(oldItem: CurrencyResponseItem, newItem: CurrencyResponseItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CurrencyResponseItem, newItem: CurrencyResponseItem): Boolean {
            return oldItem == newItem
        }
    }

}