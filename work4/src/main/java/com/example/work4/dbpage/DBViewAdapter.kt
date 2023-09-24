package com.example.work4.dbpage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.work4.R
import com.example.work4.model.film.Film

class DBViewAdapter (private val films: List<Film>) :
    RecyclerView.Adapter<DBViewAdapter.DBViewHolder>()
{
    class DBViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.textName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DBViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.db_item, parent, false)
        return DBViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DBViewHolder, position: Int) {
        val film : Film = films[position]
        holder.itemView.tag = film
        holder.name.text = film.name
    }

    override fun getItemCount() = films.size

}