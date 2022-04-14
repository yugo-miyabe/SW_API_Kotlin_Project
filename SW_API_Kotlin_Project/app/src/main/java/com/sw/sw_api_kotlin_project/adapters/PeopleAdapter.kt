package com.sw.sw_api_kotlin_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.model.peple.Result

class PeopleAdapter(private val peopleList: List<Result>) :
    RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val fullName = view.findViewById<TextView>(R.id.full_name)
        private val height = view.findViewById<TextView>(R.id.height)
        private val mass = view.findViewById<TextView>(R.id.mass)

        fun bind(people: Result) {
            fullName.text = people.name
            height.text = "height:" + people.height
            mass.text = "mass" + people.mass
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_people_list, parent, false)

        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(peopleList[position])
    }

    override fun getItemCount() = peopleList.size


}