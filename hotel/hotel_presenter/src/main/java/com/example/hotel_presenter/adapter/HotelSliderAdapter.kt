package com.example.hotel_presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hotel_presenter.databinding.ItemSliderImageBinding
import com.example.navigation.loadImage

class HotelSliderAdapter : ListAdapter<String, HotelSliderAdapter.HotelSliderViewHolder>(
    DiffUtilItemCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelSliderViewHolder {
        return HotelSliderViewHolder(
            ItemSliderImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HotelSliderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HotelSliderViewHolder(private val binding: ItemSliderImageBinding) :
        ViewHolder(binding.root) {
        fun bind(hotels: String) {
            binding.ivHotel.loadImage(hotels)
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