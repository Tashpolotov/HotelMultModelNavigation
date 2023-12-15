package com.example.room_presenter

import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.navigation.AppRoutes
import com.example.room_presenter.adapter.RoomsAdapter
import com.example.navigation.base.BaseFragment
import com.example.room_presenter.databinding.FragmentRoomBinding
import com.example.room_presenter.viewmodel.RoomsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RoomFragment : BaseFragment(R.layout.fragment_room) {

    private val binding by viewBinding(FragmentRoomBinding::bind)
    private val viewModel by lazy {
        ViewModelProvider(this)[RoomsViewModel::class.java]
    }
    private val adapter by lazy { RoomsAdapter(this::onClick, requireContext()) }

    override fun initialize() {
        binding.rvRooms.adapter = adapter
        binding.includeCustomAppBar.tvName.text = arguments?.getString(ARGS_HOTEL_NAME)
    }

    private fun onClick(){
        findNavController().navigate(AppRoutes.FeatureThird.Deeplink.FRAGMENT_THIRD)

    }
    override fun setupListeners() {
        binding.includeCustomAppBar.ibBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun setupRequests() {
        viewModel.getRooms()
    }

    override fun initSubscribers() {
        viewModel.getRoomsState.collectUIState(
            state = {
                binding.progressBar.isVisible = true
                binding.rvRooms.isVisible = false
            },
            onSuccess = {
                binding.progressBar.isVisible = false
                binding.rvRooms.isVisible = true
                adapter.submitList(it.rooms)
            }
        )
    }
    companion object {
        const val ARGS_HOTEL_NAME = "hotel_name"
    }

}