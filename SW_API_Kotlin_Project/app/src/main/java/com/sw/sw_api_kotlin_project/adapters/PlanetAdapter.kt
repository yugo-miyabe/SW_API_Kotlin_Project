package com.sw.sw_api_kotlin_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.data.model.Planet

class PlanetAdapter(private val planetList: List<Planet>, private val onClick: (Planet) -> Unit) :
    RecyclerView.Adapter<PlanetAdapter.ViewHolder>() {

    class ViewHolder(private val view: View, private val onClick: (Planet) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val planetText = view.findViewById<TextView>(R.id.planet_name_text)

        fun bind(planet: Planet) {
            planetText.text = planet.name
            view.setOnClickListener {
                onClick(planet)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_planet, parent, false)

        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(planetList[position])
    }

    override fun getItemCount() = planetList.size
}