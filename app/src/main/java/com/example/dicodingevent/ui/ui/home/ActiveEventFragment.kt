package com.example.dicodingevent.ui.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingevent.EventAdapter
import com.example.dicodingevent.data.response.CustomerReviewsItem
import com.example.dicodingevent.data.response.ResponseEvent
import com.example.dicodingevent.data.retrofit.ApiConfig
import com.example.dicodingevent.databinding.FragmentHomeBinding
import com.example.dicodingevent.ui.ReviewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActiveEventFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val eventAdapter = EventAdapter()

    companion object {
        private const val TAG = "HomeFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val layoutManager = LinearLayoutManager(requireContext())
        _binding?.rvActive?.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        _binding?.rvActive?.addItemDecoration(itemDecoration)
        _binding?.rvActive?.adapter = eventAdapter
        findRestaurant()

        return root
    }

    private fun findRestaurant() {
        showLoading(true)
        val client = ApiConfig.getApiService().getActiveEvents()
        client.enqueue(object : Callback<ResponseEvent> {
            override fun onResponse(
                call: Call<ResponseEvent>,
                response: Response<ResponseEvent>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        eventAdapter.submitList(responseBody.listEvents)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ResponseEvent>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }



    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
//            binding.progressBar.visibility = View.VISIBLE
        } else {
//            binding.progressBar.visibility = View.GONE
        }
    }
}