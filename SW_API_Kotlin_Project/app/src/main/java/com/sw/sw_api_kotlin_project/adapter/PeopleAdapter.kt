package com.sw.sw_api_kotlin_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.data.model.People

class PeopleAdapter(
    private val peopleList: List<People>,
    private val onClick: (People) -> Unit,
) :
    RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    class ViewHolder(
        private val view: View,
        private val onClick: (People) -> Unit,
    ) :
        RecyclerView.ViewHolder(view) {
        private val fullName = view.findViewById<TextView>(R.id.full_name_text)

        fun bind(people: People) {
            fullName.text = people.name
            view.setOnClickListener {
                onClick(people)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_people, parent, false)

        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(peopleList[position])
    }

    override fun getItemCount() = peopleList.size


}