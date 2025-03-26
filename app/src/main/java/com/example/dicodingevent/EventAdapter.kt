package com.example.dicodingevent

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.dicodingevent.data.response.EventsItem
import com.example.dicodingevent.databinding.EventItemBinding
import com.example.dicodingevent.ui.MainActivity

class EventAdapter : ListAdapter<EventsItem, EventItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventItemViewHolder {
        val binding = EventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventItemViewHolder(binding)
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: EventItemViewHolder, position: Int) {
        holder.bind(currentList[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MainActivity::class.java)
            intent.putExtra("key_event", currentList[holder.adapterPosition])
            holder.itemView.context.startActivity(intent)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<EventsItem>() {
            override fun areItemsTheSame(oldItem: EventsItem, newItem: EventsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: EventsItem, newItem: EventsItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}