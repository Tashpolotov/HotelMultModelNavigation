package com.example.room_presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.room_domain.model.Room
import com.example.room_presenter.R
import com.example.room_presenter.databinding.ItemRoomsBinding

class RoomsAdapter(private val onClick: () -> Unit, private val context: Context) :
    ListAdapter<Room, RoomsAdapter.RoomsViewHolder>(
        DiffUtilItemCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder {
        return RoomsViewHolder(
            ItemRoomsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RoomsViewHolder(private val binding: ItemRoomsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(room: Room) {
            val adapter = HotelSliderAdapter()
            val adapterPeculiarities = PeculiaritiesAdapter()
            binding.rvHotels.adapter = adapterPeculiarities
            binding.viewPager2.adapter = adapter
            binding.indicator.setViewPager(binding.viewPager2)
            adapter.registerAdapterDataObserver(binding.indicator.adapterDataObserver)
            adapter.submitList(room.image_urls)
            binding.tvName.text = room.name
            binding.tvPriceForIt.text = room.price_per
            binding.tvMinimalPrice.text = context.getString(
                R.string.charge,
                room.price
            )
            adapterPeculiarities.submitList(room.peculiarities)
            binding.btnChooseNumber.setOnClickListener {
                onClick()
            }
        }
    }

    private class DiffUtilItemCallback : DiffUtil.ItemCallback<Room>() {

        override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
            return oldItem == newItem
        }
    }
}