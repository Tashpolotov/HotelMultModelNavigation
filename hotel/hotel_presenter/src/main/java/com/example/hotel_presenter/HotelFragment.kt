package com.example.hotel_presenter


import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.hotel_presenter.adapter.HotelsAdapter
import com.example.hotel_presenter.databinding.FragmentHotelBinding
import com.example.hotel_presenter.viewmodel.HotelsViewModel
import com.example.navigation.AppRoutes
import com.example.navigation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotelFragment : BaseFragment(R.layout.fragment_hotel) {

    private val binding by viewBinding(FragmentHotelBinding::bind)
    private val hotelsViewModel by lazy {
        ViewModelProvider(this)[HotelsViewModel::class.java]
    }
    private val adapter by lazy { HotelsAdapter(requireContext()) }
    private var selectedHotelName: String? = null

    private val navController by lazy {
        findNavController()
    }


    override fun initialize() {
        binding.rvHotels.adapter = adapter

    }

    override fun setupRequests() {
        hotelsViewModel.getHotels()
    }

    override fun setupSubscribers() {
    }

    override fun setupListeners() {
        binding.btnToChooseNumber.setOnClickListener {
            selectedHotelName?.let { name ->
                navController.navigate(AppRoutes.FeatureSecond.Deeplink.FRAGMENT_SECOND)
            }
        }
    }

    override fun initSubscribers() {
        hotelsViewModel.getHotelStar.collectUIState(
            state = {
                binding.progressBar.isVisible = true
                binding.rvHotels.isVisible = false
                binding.flBtn.isVisible = false
            },
            onSuccess = {
                adapter.submitList(listOf(it))
                selectedHotelName = it.name
                binding.progressBar.isVisible = false
                binding.rvHotels.isVisible = true
                binding.flBtn.isVisible = true
            }
        )
    }

    companion object {
        const val ARGS_HOTEL_NAME = "hotel_name"
    }
}