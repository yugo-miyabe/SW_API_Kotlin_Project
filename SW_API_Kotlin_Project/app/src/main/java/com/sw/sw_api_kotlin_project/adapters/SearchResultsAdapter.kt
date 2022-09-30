package com.sw.sw_api_kotlin_project.adapters

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

// TODO 構成を考え直す
class SearchResultsAdapter(private val searchResults: List<Results<out Parcelable>>) :
    RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    private val peopleMaxCount = searchResults[0].results.size
    private val filmMaxCount = searchResults[1].results.size
    private val planetMaxCount = searchResults[2].results.size


    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val searchResultImage = view.findViewById<ImageView>(R.id.search_result_image)
        private val searchResultText = view.findViewById<TextView>(R.id.search_result_text)

        fun peopleBind(people: People) {
            searchResultImage.setImageResource(R.drawable.ic_baseline_face_24)
            searchResultText.text = people.name
        }

        fun filmBind(film: Film) {
            searchResultImage.setImageResource(R.drawable.ic_baseline_film_24)
            searchResultText.text = film.title
        }

        fun planetBind(planet: Planet) {
            searchResultImage.setImageResource(R.drawable.ic_baseline_planets_24)
            searchResultText.text = planet.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search_result, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position <= peopleMaxCount) {
            holder.peopleBind(searchResults[0].results[position] as People)
        } else if (position <= filmMaxCount) {
            holder.filmBind(searchResults[0].results[position - peopleMaxCount] as Film)
        } else if (position <= planetMaxCount) {
            holder.planetBind(searchResults[0].results[position - (peopleMaxCount + planetMaxCount)] as Planet)
        }
    }

    override fun getItemCount(): Int {
        return peopleMaxCount + filmMaxCount + planetMaxCount
    }

}