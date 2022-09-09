package com.sw.sw_api_kotlin_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.data.model.Planet

class PlanetAdapter(private val planetList: List<Planet>) :
    RecyclerView.Adapter<PlanetAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val planet = view.findViewById<TextView>(R.id.planet_name_text)

        fun bind(planetList: Planet) {
            planet.text = planetList.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_planet, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(planetList[position])
    }

    override fun getItemCount() = planetList.size
}