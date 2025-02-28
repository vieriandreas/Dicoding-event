package com.example.dicodingevent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingevent.data.response.ListEventsItem
import com.example.dicodingevent.databinding.EventItemBinding

class EventAdapter : RecyclerView.Adapter<EventItemViewHolder>() {

    private val list = mutableListOf<ListEventsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventItemViewHolder {
        val binding = EventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventItemViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: EventItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun submitList(listEventsItem: List<ListEventsItem>) {
        list.addAll(listEventsItem)
        notifyDataSetChanged()
    }

}