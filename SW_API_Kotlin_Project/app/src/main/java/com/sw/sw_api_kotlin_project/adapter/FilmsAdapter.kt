package com.sw.sw_api_kotlin_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.data.model.Film

class FilmsAdapter(private val filmList: List<Film>, private val onClick: (Film) -> Unit) :
    RecyclerView.Adapter<FilmsAdapter.ViewHolder>() {

    class ViewHolder(private val view: View, private val onClick: (Film) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val moveImage = view.findViewById<ImageView>(R.id.common_image)
        private val moveTitle = view.findViewById<TextView>(R.id.common_text)

        fun bind(film: Film) {
            moveImage.setImageResource(R.drawable.ic_baseline_film_24)
            moveTitle.text = film.title
            view.setOnClickListener {
                onClick(film)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_common, parent, false)

        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filmList[position])
    }

    override fun getItemCount() = filmList.size


}