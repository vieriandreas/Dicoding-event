package com.example.dicodingevent.ui.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingevent.EventAdapter
import com.example.dicodingevent.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val eventAdapter = EventAdapter()
    private lateinit var viewModel : FavoriteViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(FavoriteViewModel::class.java)

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val layoutManager = LinearLayoutManager(requireContext())
        _binding?.rvFavorite?.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        _binding?.rvFavorite?.addItemDecoration(itemDecoration)
        _binding?.rvFavorite?.adapter = eventAdapter

        viewModel.getFavorite()

        observeActiveEvent()

        return root
    }

    private fun observeActiveEvent() {
        viewModel.event.observe(viewLifecycleOwner) { status ->
            when(status) {
                FavoriteStatus.Error -> {}
                is FavoriteStatus.Loading -> {showLoading(status.loading)}
                is FavoriteStatus.Succes -> {eventAdapter.submitList(status.list)}
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