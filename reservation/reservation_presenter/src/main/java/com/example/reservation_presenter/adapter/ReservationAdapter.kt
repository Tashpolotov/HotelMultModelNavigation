package com.example.reservation_presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.navigation.editTextIsEmpty
import com.example.navigation.setupTextWatcherEmail
import com.example.navigation.setupTextWatcherPhoneNumber
import com.example.navigation.showToast
import com.example.reservation_domain.model.Reservation
import com.example.reservation_presenter.R
import com.example.reservation_presenter.databinding.ItemReservationBinding

class ReservationAdapter(
    private val btnPay: Button,
    private val navController: NavController,
    private val context: Context,
) : ListAdapter<Reservation, ReservationAdapter.ReservationViewHolder>(
    DiffUtilItemCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        return ReservationViewHolder(
            ItemReservationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ReservationViewHolder(private val binding: ItemReservationBinding) :
        ViewHolder(binding.root) {

        private fun buyerInformationCheck() {
            binding.apply {
                if (edEmailLayout.error != null || edPhoneLayout.error != null
                ) {
                    context.showToast(context.getString(R.string.please_fill_out_all_fields_correctly))
                } else if (edEmail.text.toString()
                        .isEmpty() || edPhone.text.toString().isEmpty()
                ) {
                    context.showToast(context.getString(R.string.fields_cannot_be_empty))
                    editTextIsEmpty(
                        edPhone, edPhoneLayout, context.getString(R.string.fields_cannot_be_empty)
                    )
                    editTextIsEmpty(
                        edEmail, edEmailLayout, context.getString(R.string.fields_cannot_be_empty)
                    )
                } else {
                    edEmailLayout.error = null
                    edPhoneLayout.error = null
                    edPhoneLayout.isErrorEnabled = false
                    edEmailLayout.isErrorEnabled = false
                    navController.navigate(R.id.payFragment2)
                }
            }
        }

        fun bind(reservation: Reservation) {

            val adapter = TouristAdapter({ buyerInformationCheck() }, btnPay, navController, context)
            binding.rvTourists.adapter = adapter
            binding.ibAddTourist.setOnClickListener {
                adapter.addItem()
                if (adapter.isMaxTouristsReached()) {
                    binding.clAddTourist.visibility = View.GONE
                }
            }

            binding.tvRating.text =
                context.getString(R.string.rating, reservation.horating, reservation.rating_name)
            binding.tvName.text = reservation.hotel_name
            binding.tvAdress.text = reservation.hotel_adress
            binding.tvDepartureCounty.text = reservation.departure
            binding.tvArrivalCountry.text = reservation.arrival_country
            binding.tvDate.text = context.getString(
                R.string.date,
                reservation.tour_date_start,
                reservation.tour_date_stop
            )
            binding.tvNights.text = context.getString(R.string.nights, reservation.number_of_nights)
            binding.tvHotelName.text = reservation.hotel_name
            binding.tvRoomInfo.text = reservation.room
            binding.tvNutritionInfo.text = reservation.nutrition
            binding.tvTourPrice.text =
                context.getString(R.string.tour_price, reservation.tour_price)
            binding.tvPriceFuelCharge.text =
                context.getString(R.string.charge, reservation.fuel_charge)
            binding.tvPriceServiceCharge.text =
                context.getString(R.string.charge, reservation.service_charge)
            binding.tvPriceToPay.text = context.getString(
                R.string.charge,
                reservation.tour_price
                        + reservation.fuel_charge
                        + reservation.service_charge
            )

            binding.edPhone.setText("+7 (")
            binding.edPhone.text?.let { binding.edPhone.setSelection(it.length) }

            setupTextWatcherPhoneNumber(
                binding.edPhone,
                binding.edPhoneLayout,
                "-",
                "(",
                ") ",
                context.getString(R.string.incorrect_phone_number)
            )
            setupTextWatcherEmail(
                binding.edEmail,
                binding.edEmailLayout,
                context.getString(R.string.incorrect_mail)
            )

        }
    }

    private class DiffUtilItemCallback : DiffUtil.ItemCallback<Reservation>() {

        override fun areItemsTheSame(oldItem: Reservation, newItem: Reservation): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Reservation, newItem: Reservation): Boolean {
            return oldItem == newItem
        }
    }
}