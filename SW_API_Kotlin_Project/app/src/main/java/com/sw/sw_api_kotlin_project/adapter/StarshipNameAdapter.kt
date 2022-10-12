package com.sw.sw_api_kotlin_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.data.model.Starships

// TODO 宇宙船一覧作成時に使用
class StarshipNameAdapter(
    private val starshipsList: List<Starships>,
    private val onClick: (Starships) -> Unit
) : RecyclerView.Adapter<StarshipNameAdapter.ViewHolder>() {

    class ViewHolder(
        private val view: View,
        private val onClick: (Starships) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById<ImageView>(R.id.common_image)
        private val starshipName = view.findViewById<TextView>(R.id.common_text)

        fun bind(starships: Starships) {
            imageView.setImageResource(R.drawable.ic_baseline_starships_24)
            starshipName.text = starships.name
            view.setOnClickListener {
                onClick(starships)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_common, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(starshipsList[position])

    override fun getItemCount(): Int = starshipsList.size

}