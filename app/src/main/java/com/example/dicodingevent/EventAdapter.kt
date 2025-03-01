package com.example.dicodingevent

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingevent.data.response.EventsItem
import com.example.dicodingevent.databinding.EventItemBinding
import com.example.dicodingevent.ui.MainActivity

class EventAdapter : RecyclerView.Adapter<EventItemViewHolder>() {

    private val list = mutableListOf<EventsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventItemViewHolder {
        val binding = EventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventItemViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: EventItemViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MainActivity::class.java)
            intent.putExtra("key_event", list[holder.adapterPosition])
            holder.itemView.context.startActivity(intent)
        }
    }

    fun submitList(listEventsItem: List<EventsItem>) {
        list.addAll(listEventsItem)
        notifyDataSetChanged()
    }

}