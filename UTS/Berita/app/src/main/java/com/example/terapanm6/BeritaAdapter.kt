package com.example.terapanm6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BeritaAdapter(val slideList: ArrayList<Berita>):
    RecyclerView.Adapter<BeritaAdapter.ViewHolder>(){
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val user: Berita = slideList[position]
            holder.textViewName.text = user.judul
            holder.textViewNumber.text = user.waktu
            holder.textViewAddress.text = user.penulis
            holder.textViewIsi.text = user.isi
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent?.context).inflate(R.layout.list_berita, parent, false)
            return ViewHolder(v)
        }

        override fun getItemCount(): Int {
            return slideList.size
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textViewName = itemView.findViewById(R.id.textViewName) as TextView
            val textViewNumber = itemView.findViewById(R.id.textViewNumber) as TextView
            val textViewAddress = itemView.findViewById(R.id.textViewAddress) as TextView
            val textViewIsi = itemView.findViewById(R.id.textViewIsi) as TextView
        }
}