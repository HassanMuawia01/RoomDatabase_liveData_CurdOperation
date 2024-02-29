package com.example.hassanmuawia_task.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hassanmuawia_task.R
import com.example.hassanmuawia_task.room_mvvm.MyData

class Fragment2Adapter(private val onDeleteClickListener: (MyData) -> Unit) :
    ListAdapter<MyData, Fragment2Adapter.MyDataViewHolder>(MyDataDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDataViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return MyDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyDataViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class MyDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.textView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onDeleteClickListener(getItem(position))
                }
            }
        }

        fun bind(data: MyData) {
            textView.text = data.text
        }
    }
}

class MyDataDiffCallback : DiffUtil.ItemCallback<MyData>() {
    override fun areItemsTheSame(oldItem: MyData, newItem: MyData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MyData, newItem: MyData): Boolean {
        return oldItem == newItem
    }
}
