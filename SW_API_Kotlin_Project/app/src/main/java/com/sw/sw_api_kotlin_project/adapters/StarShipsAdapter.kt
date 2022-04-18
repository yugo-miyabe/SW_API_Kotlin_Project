package com.sw.sw_api_kotlin_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.model.starships.Starships

class StarShipsAdapter(private val starships: List<Starships>) :
    RecyclerView.Adapter<StarShipsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val starshipsName = view.findViewById<TextView>(R.id.starships_name_text)
        private val modelName = view.findViewById<TextView>(R.id.starships_model_name_text)
        private val manufacturerName = view.findViewById<TextView>(R.id.starships_manufacturer_name_text)

        fun bind(starships: Starships) {
            starshipsName.text = starships.name
            modelName.text = starships.model
            manufacturerName.text = starships.manufacturer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_starships, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(starships[position])
    }

    override fun getItemCount() = starships.size
}