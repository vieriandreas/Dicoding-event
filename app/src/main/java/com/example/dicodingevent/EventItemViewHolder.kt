package com.example.dicodingevent

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dicodingevent.data.response.ListEventsItem
import com.example.dicodingevent.databinding.EventItemBinding

class EventItemViewHolder (private val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: ListEventsItem) {
        Glide.with(binding.root.context)
            .load(data.imageLogo)
            .into(binding.imgItemEvent)

        binding.tvEventName.text = data.name
    }
}
