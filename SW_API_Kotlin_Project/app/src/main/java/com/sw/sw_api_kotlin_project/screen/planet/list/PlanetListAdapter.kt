package com.sw.sw_api_kotlin_project.screen.planet.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.data.network.model.Planet

class PlanetListAdapter(private val onClick: (Planet) -> Unit) :
    PagingDataAdapter<Planet, PlanetListAdapter.PlanetListViewHolder>(PLANET_DIFF_CALLBACK) {

    class PlanetListViewHolder(private val view: View, private val onClick: (Planet) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById<ImageView>(R.id.common_image)
        private val planetText = view.findViewById<TextView>(R.id.common_text)

        fun bind(planet: Planet) {
            imageView.setImageResource(R.drawable.ic_baseline_planets_24)
            planetText.text = planet.name
            view.setOnClickListener {
                onClick(planet)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_common, parent, false)
        return PlanetListViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: PlanetListViewHolder, position: Int) {
        val planet = getItem(position)
        if (planet != null) holder.bind(planet)
    }


    companion object {
        private val PLANET_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Planet>() {
            override fun areItemsTheSame(oldItem: Planet, newItem: Planet): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Planet, newItem: Planet): Boolean =
                oldItem == newItem
        }
    }
}