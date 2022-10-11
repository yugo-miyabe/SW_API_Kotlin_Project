package com.sw.sw_api_kotlin_project.adapter

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.data.model.Film
import com.sw.sw_api_kotlin_project.data.model.People
import com.sw.sw_api_kotlin_project.data.model.Planet
import com.sw.sw_api_kotlin_project.data.model.Results
import com.sw.sw_api_kotlin_project.utils.ListType

class SearchResultsAdapter(
    private val searchResults: List<Results<out Parcelable>>,
    private val onPeopleClick: (People) -> Unit,
    private val onFilmClick: (Film) -> Unit,
    private val onPlanetClick: (Planet) -> Unit
) :
    RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    private val peopleMaxCount = searchResults[ListType.PEOPLE.ordinal].results.size
    private val filmMaxCount = searchResults[ListType.FILM.ordinal].results.size
    private val planetMaxCount = searchResults[ListType.PLANETS.ordinal].results.size

    class ViewHolder(
        private val view: View,
        private val onPeopleClick: (People) -> Unit,
        private val onFilmClick: (Film) -> Unit,
        private val onPlanetClick: (Planet) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val searchResultImage = view.findViewById<ImageView>(R.id.search_result_image)
        private val searchResultText = view.findViewById<TextView>(R.id.search_result_text)

        fun peopleBind(people: People) {
            searchResultImage.setImageResource(R.drawable.ic_baseline_face_24)
            searchResultText.text = people.name
            view.setOnClickListener {
                onPeopleClick(people)
            }
        }

        fun filmBind(film: Film) {
            searchResultImage.setImageResource(R.drawable.ic_baseline_film_24)
            searchResultText.text = film.title
            view.setOnClickListener {
                onFilmClick(film)
            }
        }

        fun planetBind(planet: Planet) {
            searchResultImage.setImageResource(R.drawable.ic_baseline_planets_24)
            searchResultText.text = planet.name
            view.setOnClickListener {
                onPlanetClick(planet)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search_result, parent, false)

        return ViewHolder(view, onPeopleClick, onFilmClick, onPlanetClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position < peopleMaxCount) {
            holder.peopleBind(searchResults[ListType.PEOPLE.ordinal].results[position] as People)
        } else if (position < filmMaxCount + peopleMaxCount) {
            holder.filmBind(searchResults[ListType.FILM.ordinal].results[position - peopleMaxCount] as Film)
        } else if (position < planetMaxCount + filmMaxCount + peopleMaxCount) {
            holder.planetBind(searchResults[ListType.PLANETS.ordinal].results[position - (peopleMaxCount + filmMaxCount)] as Planet)
        }
    }

    override fun getItemCount(): Int = peopleMaxCount + filmMaxCount + planetMaxCount

}