package com.example.reservation_presenter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.navigation.base.BaseFragment
import com.example.reservation_presenter.adapter.ReservationAdapter
import com.example.reservation_presenter.databinding.FragmentReservationBinding
import com.example.reservation_presenter.viewmodel.ReservationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationFragment : BaseFragment(R.layout.fragment_reservation) {

    private val binding by viewBinding(FragmentReservationBinding::bind)
    private val adapter by lazy {
        ReservationAdapter(
            binding.btnPay,
            findNavController(),
            requireContext()
        )
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[ReservationViewModel::class.java]
    }

    override fun initialize() {
        binding.rvReservation.adapter = adapter
        binding.includeCustomAppBar.tvName.text = getString(R.string.reservation)

    }

    override fun setupRequests() {
        viewModel.getReservation()
    }

    override fun initSubscribers() {
        viewModel.getReservationState.collectUIState(
            state = {
                binding.progressBar.isVisible = true
                binding.rvReservation.isVisible = false
                binding.flBtn.isVisible = false
            },
            onSuccess = {
                binding.progressBar.isVisible = false
                binding.rvReservation.isVisible = true
                binding.flBtn.isVisible = true
                adapter.submitList(listOf(it))
                binding.btnPay.text =
                    getString(
                        R.string.paid_price,
                        it.tour_price + it.fuel_charge + it.service_charge
                    )
            }
        )
    }

    override fun setupListeners() {
        binding.includeCustomAppBar.ibBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}