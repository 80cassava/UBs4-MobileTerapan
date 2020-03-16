package com.example.terapanm4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SlideshowAdapter(val slideList: ArrayList<Slideshow>) :
    RecyclerView.Adapter<SlideshowAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewJudul = itemView.findViewById(R.id.textViewJudul) as TextView
        val textViewUrl = itemView.findViewById(R.id.textViewUrl) as TextView
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: Slideshow = slideList[position]
        holder.textViewJudul.text = user.name
        holder.textViewUrl.text = user.address
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.list_slide, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return slideList.size
    }
}