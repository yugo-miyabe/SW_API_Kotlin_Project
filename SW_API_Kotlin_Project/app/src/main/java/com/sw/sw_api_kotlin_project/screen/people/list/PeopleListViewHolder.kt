package com.sw.sw_api_kotlin_project.screen.people.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.network.model.People

class PeopleListViewHolder(
    private val view: View,
    private val onClick: (People) -> Unit,
) : RecyclerView.ViewHolder(view) {
    private val imageView = view.findViewById<ImageView>(R.id.common_image)
    private val fullName = view.findViewById<TextView>(R.id.common_text)

    fun bind(people: People) {
        imageView.setImageResource(R.drawable.ic_baseline_face_24)
        fullName.text = people.name
        view.setOnClickListener {
            onClick(people)
        }
    }
}