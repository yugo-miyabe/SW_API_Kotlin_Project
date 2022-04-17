package com.sw.sw_api_kotlin_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.model.films.Films

class FilmsAdapter(private val filmsList: List<Films>) :
    RecyclerView.Adapter<FilmsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val move = view.findViewById<TextView>(R.id.move_name)
        private val director = view.findViewById<TextView>(R.id.director_name)
        private val producer = view.findViewById<TextView>(R.id.producer_name)

        fun bind(films: Films) {
            move.text = films.title
            director.text = films.director
            producer.text = films.producer
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_films, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filmsList[position])
    }

    override fun getItemCount() = filmsList.size


}