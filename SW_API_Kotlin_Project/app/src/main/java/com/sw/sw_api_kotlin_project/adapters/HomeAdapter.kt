package com.sw.sw_api_kotlin_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.data.model.HomeItem

class HomeAdapter(private val homeItemList: List<HomeItem>, private val onClick: () -> Unit) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    class ViewHolder(private val view: View, private val onClick: () -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val image = view.findViewById<ImageView>(R.id.home_image)
        private val text = view.findViewById<TextView>(R.id.home_text)

        fun bind(homeItem: HomeItem) {
            text.text = homeItem.title
            view.setOnClickListener {
                onClick()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)

        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(homeItemList[position])
    }

    override fun getItemCount(): Int = homeItemList.size
}