package com.sw.sw_api_kotlin_project.screen.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.data.model.entity.Favorite
import com.sw.sw_api_kotlin_project.data.network.model.Film
import com.sw.sw_api_kotlin_project.data.network.model.People
import com.sw.sw_api_kotlin_project.data.network.model.Planet
import com.sw.sw_api_kotlin_project.data.model.entity.ListType

class FavoriteAdapter(
    private val favoriteList: List<Favorite>,
    private val onPeopleClick: (People) -> Unit,
    private val onFilmClick: (Film) -> Unit,
    private val onPlanetClick: (Planet) -> Unit
) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    class ViewHolder(
        private val view: View,
        private val onPeopleClick: (People) -> Unit,
        private val onFilmClick: (Film) -> Unit,
        private val onPlanetClick: (Planet) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById<ImageView>(R.id.common_image)
        private val titleText = view.findViewById<TextView>(R.id.common_text)

        fun bind(favorite: Favorite) {
            when (favorite.listType) {
                ListType.PEOPLE -> {
                    val people = favorite.people!!
                    imageView.setImageResource(R.drawable.ic_baseline_face_24)
                    titleText.text = people.name
                    view.setOnClickListener {
                        onPeopleClick(people)
                    }
                }
                ListType.FILM -> {
                    val film = favorite.film!!
                    imageView.setImageResource(R.drawable.ic_baseline_film_24)
                    titleText.text = film.title
                    view.setOnClickListener {
                        onFilmClick(film)
                    }
                }
                ListType.PLANETS -> {
                    val planet = favorite.planet!!
                    imageView.setImageResource(R.drawable.ic_baseline_planets_24)
                    titleText.text = planet.name
                    view.setOnClickListener {
                        onPlanetClick(planet)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_common, parent, false)
        return ViewHolder(view, onPeopleClick, onFilmClick, onPlanetClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(favoriteList[position])


    override fun getItemCount() = favoriteList.size

}
