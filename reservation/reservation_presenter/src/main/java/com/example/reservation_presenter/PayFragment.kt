package com.example.reservation_presenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.navigation.AppRoutes
import com.example.navigation.base.BaseFragment
import com.example.reservation_presenter.databinding.FragmentPayBinding
import com.example.reservation_presenter.databinding.FragmentReservationBinding


class PayFragment : BaseFragment(R.layout.fragment_pay) {
    private val binding by viewBinding(FragmentPayBinding::bind)
    private val navController by lazy {
        findNavController()
    }

    override fun initSubscribers() {
        binding.btnSuper.setOnClickListener{
            navController.navigate(AppRoutes.FeatureFirst.Deeplink.FRAGMENT_FIRST)
        }
    }
}