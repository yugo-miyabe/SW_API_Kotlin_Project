package com.sw.sw_api_kotlin_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.data.database.Favorite
import com.sw.sw_api_kotlin_project.utils.ListType

class FavoriteAdapter(private val favoriteList: List<Favorite>) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById<ImageView>(R.id.search_result_image)
        private val titleText = view.findViewById<TextView>(R.id.search_result_text)

        fun bind(favorite: Favorite) {
            when (favorite.listType) {
                ListType.PEOPLE -> {
                    imageView.setImageResource(R.drawable.ic_baseline_face_24)
                    titleText.text = favorite.people?.name
                }
                ListType.FILM -> {
                    imageView.setImageResource(R.drawable.ic_baseline_film_24)
                    titleText.text = favorite.film?.title
                }
                ListType.PLANETS -> {
                    imageView.setImageResource(R.drawable.ic_baseline_planets_24)
                    titleText.text = favorite.planet?.name
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_result, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(favoriteList[position])


    override fun getItemCount() = favoriteList.size

}
