package com.example.dicodingevent.ui.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingevent.EventAdapter
import com.example.dicodingevent.databinding.FragmentPastBinding
import com.example.dicodingevent.ui.ui.home.ActiveEventViewModel
import com.example.dicodingevent.ui.ui.home.EventStatus

class PastEventFragment : Fragment() {

    private var _binding: FragmentPastBinding? = null
    private val eventAdapter = EventAdapter()
    private lateinit var viewModel : PastEventViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(PastEventViewModel::class.java)

        _binding = FragmentPastBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val layoutManager = LinearLayoutManager(requireContext())
        _binding?.rvPast?.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        _binding?.rvPast?.addItemDecoration(itemDecoration)
        _binding?.rvPast?.adapter = eventAdapter

        viewModel.getPastEvent()

        observeActiveEvent()

        return root
    }

    private fun observeActiveEvent() {
        viewModel.event.observe(viewLifecycleOwner) { status ->
            when(status) {
                PastEventStatus.Error -> {}
                is PastEventStatus.Loading -> {showLoading(status.loading)}
                is PastEventStatus.Succes -> {eventAdapter.submitList(status.list)}
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}