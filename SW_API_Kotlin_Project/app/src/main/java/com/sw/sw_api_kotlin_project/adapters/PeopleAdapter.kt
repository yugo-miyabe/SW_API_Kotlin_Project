package com.sw.sw_api_kotlin_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.data.model.People
import kotlinx.coroutines.Job

class PeopleAdapter(
    private val peopleList: List<People>,
    private val checkFavorite: (String) -> Boolean,
    private val onClick: (People) -> Unit,
    private val onFavoriteClick: (String) -> Unit,
) :
    RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    class ViewHolder(
        private val view: View,
        private val checkFavorite: (String) -> Boolean,
        private val onClick: (People) -> Unit,
        private val onFavoriteClick: (String) -> Unit
    ) :
        RecyclerView.ViewHolder(view) {
        private val fullName = view.findViewById<TextView>(R.id.full_name_text)
        private val peopleFavoriteMark = view.findViewById<ImageView>(R.id.people_favorite_mark)

        fun bind(people: People) {
            fullName.text = people.name
            view.setOnClickListener {
                onClick(people)
            }
            peopleFavoriteMark.setOnClickListener {
                onFavoriteClick(people.name)
            }
            if (checkFavorite(people.name)) {
                peopleFavoriteMark.setImageResource(R.drawable.ic_baseline_star_24)
            } else {
                peopleFavoriteMark.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_people, parent, false)

        return ViewHolder(view, checkFavorite, onClick, onFavoriteClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(peopleList[position])
    }

    override fun getItemCount() = peopleList.size


}