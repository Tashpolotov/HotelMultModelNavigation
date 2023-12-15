package com.example.hotel_presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hotel_presenter.R
import com.example.hotel_presenter.databinding.ItemHotelsBinding
import com.example.hotels_domain.model.Hotels

class HotelsAdapter(private val context: Context) : ListAdapter<Hotels, HotelsAdapter.HotelsViewHolder>(
    DiffUtilItemCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHotelsBinding.inflate(inflater, parent, false)
        return HotelsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HotelsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HotelsViewHolder(private val binding: ItemHotelsBinding) :
        ViewHolder(binding.root) {
        fun bind(hotels: Hotels) {
            val adapter = HotelSliderAdapter()
            val adapterPeculiarities = PeculiaritiesAdapter()
            binding.rvHotels.adapter = adapterPeculiarities
            binding.viewPager2.adapter = adapter
            binding.indicator.setViewPager(binding.viewPager2)
            adapter.registerAdapterDataObserver(binding.indicator.adapterDataObserver)
            adapter.submitList(hotels.image_urls)
            binding.tvAdress.text = hotels.adress
            binding.tvName.text = hotels.name
            binding.tvRating.text =
                context.getString(R.string.rating, hotels.rating, hotels.rating_name)
            binding.tvPriceForIt.text = hotels.price_for_it
            binding.tvMinimalPrice.text =
                context.getString(R.string.minimal_price, hotels.minimal_price)
            adapterPeculiarities.submitList(hotels.about_the_hotel.peculiarities)
            binding.tvDescription.text = hotels.about_the_hotel.description
        }
    }

    private class DiffUtilItemCallback : DiffUtil.ItemCallback<Hotels>() {

        override fun areItemsTheSame(oldItem: Hotels, newItem: Hotels): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Hotels, newItem: Hotels): Boolean {
            return oldItem == newItem
        }
    }
}