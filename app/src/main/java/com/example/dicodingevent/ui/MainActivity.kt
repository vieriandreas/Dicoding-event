package com.example.dicodingevent.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.dicodingevent.data.database.FavoriteEntity
import com.example.dicodingevent.data.database.ViewModelFactory
import com.example.dicodingevent.data.response.EventsItem
import com.example.dicodingevent.data.response.convertEventItemToFavoriteEvent
import com.example.dicodingevent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private var isFavorite = false
    private var favoriteEvent: FavoriteEntity? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val dataEvent = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<EventsItem>("key_event", EventsItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<EventsItem>("key_event")
        }

        Log.d("Event", dataEvent?.name ?: "data event Null")

        supportActionBar?.title = dataEvent?.name

        if (dataEvent != null) {
            Glide.with(this)
                .load(dataEvent.imageLogo)
                .into(binding.ivLogo)

            binding.tvEventName.text = dataEvent?.name
            binding.tvOwnerName.text = "Owner: ${dataEvent?.ownerName}"
            binding.tvBegginTime.text = "waktu: ${dataEvent?.beginTime}"
            val quota = dataEvent.quota
            val registrant = dataEvent.registrants
            val remainingQuota = quota - registrant
            binding.tvQuota.text = "Kuota tersedia: ${remainingQuota}"
            binding.tvDescription.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(dataEvent.description, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(dataEvent.description)
            }
            binding.btnLinkEvent.setOnClickListener {
                if (dataEvent != null) {
                    openEvent(dataEvent.link)
                }
            }
        }

        mainViewModel = obtainViewModel(this@MainActivity)

        binding.fabFavorite.setOnClickListener {
            if (dataEvent != null) {
                mainViewModel.insert(dataEvent)
            }
        }




    }

    private fun setFavoriteIcon(isFavorite: Boolean) {
        if (isFavorite) {
            binding.fabFavorite.setImageResource(com.example.dicodingevent.R.drawable.dark_theme)
        } else {
            binding.fabFavorite.setImageResource(com.example.dicodingevent.R.drawable.favorite)
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(MainViewModel::class.java)
    }

    private fun openEvent(link: String) {
        val intent = Intent.parseUri(link, Intent.URI_INTENT_SCHEME)
        startActivity(intent)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
