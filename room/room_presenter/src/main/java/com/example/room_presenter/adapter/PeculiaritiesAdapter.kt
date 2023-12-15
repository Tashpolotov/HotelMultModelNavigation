package com.example.room_presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.room_presenter.databinding.ItemPeculiaritiesBinding

class PeculiaritiesAdapter : ListAdapter<String, PeculiaritiesAdapter.PeculiaritiesViewHolder>(
    DiffUtilItemCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeculiaritiesViewHolder {
        return PeculiaritiesViewHolder(
            ItemPeculiaritiesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PeculiaritiesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PeculiaritiesViewHolder(private val binding: ItemPeculiaritiesBinding) :
        ViewHolder(binding.root) {
        fun bind(peculiarities: String) {
            binding.tvPeculiarities.text = peculiarities
        }
    }

    private class DiffUtilItemCallback : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}