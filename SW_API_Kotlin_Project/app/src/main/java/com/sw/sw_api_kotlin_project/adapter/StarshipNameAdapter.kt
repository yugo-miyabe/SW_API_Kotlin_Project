package com.sw.sw_api_kotlin_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.data.model.Starships

class StarshipNameAdapter(private val starshipsList: List<Starships>) :
    RecyclerView.Adapter<StarshipNameAdapter.ViewHolder>() {

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val starshipName = view.findViewById<TextView>(R.id.starship_text)

        fun bind(starships: Starships) {
            starshipName.text = starships.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_starship, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(starshipsList[position])
    }

    override fun getItemCount(): Int = starshipsList.size

}