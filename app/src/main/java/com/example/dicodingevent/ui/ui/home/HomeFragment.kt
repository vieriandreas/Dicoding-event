package com.example.dicodingevent.ui.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.dicodingevent.data.response.CustomerReviewsItem
import com.example.dicodingevent.data.response.ResponseEvent
import com.example.dicodingevent.data.response.ResponseItem
import com.example.dicodingevent.data.retrofit.ApiConfig
import com.example.dicodingevent.databinding.ActivityMainBinding
import com.example.dicodingevent.ui.ReviewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var binding: ActivityMainBinding
    companion object {
        private const val TAG = "HomeFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val layoutManager = LinearLayoutManager(this)
        binding.rvReview.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvReview.addItemDecoration(itemDecoration)
        findRestaurant()
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
                        setActiveData(responseBody.listEvents)
                        setPastData(responseBody.listEvents.)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ResponseItem>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
    private fun setActiveData(responseEvent: ResponseEvent) {
        binding.tvTitle.text = responseEvent.name
        binding.tvDescription.text = responseEvent.description
        Glide.with(this@HomeFragment)
            .load("https://restaurant-api.dicoding.dev/images/large/${responseEvent.pictureId}")
            .into(binding.ivPicture)
    }
    private fun setPastData(consumerReviews: List<CustomerReviewsItem>) {
        val adapter = ReviewAdapter()
        adapter.submitList(consumerReviews)
        binding.rvReview.adapter = adapter
        binding.edReview.setText("")
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


}