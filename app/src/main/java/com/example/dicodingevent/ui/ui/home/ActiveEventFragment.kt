package com.example.dicodingevent.ui.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingevent.EventAdapter
import com.example.dicodingevent.databinding.FragmentActiveBinding


class ActiveEventFragment : Fragment() {

    private var _binding: FragmentActiveBinding? = null
    private val binding get() = _binding!!

    private val eventAdapter = EventAdapter()
    private lateinit var viewModel : ActiveEventViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActiveBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(requireActivity())[ActiveEventViewModel::class.java]

        val layoutManager = LinearLayoutManager(requireContext())
        _binding?.rvActive?.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        _binding?.rvActive?.addItemDecoration(itemDecoration)
        _binding?.rvActive?.adapter = eventAdapter

        viewModel.getActiveEvent()

        observeActiveEvent()

        return root
    }

    private fun observeActiveEvent() {
        viewModel.event.observe(viewLifecycleOwner) { status ->
            when(status) {
                EventStatus.Error -> {}
                is EventStatus.Loading -> {showLoading(status.loading)}
                is EventStatus.Succes -> {eventAdapter.submitList(status.list)}
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}