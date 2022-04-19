package com.sw.sw_api_kotlin_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.model.root.HomeData

class APIRootAdapter(
    private val apiRootList: List<HomeData>,
    private val onClick: (Fragment) -> Unit
) :
    RecyclerView.Adapter<APIRootAdapter.ViewHolder>() {

    class ViewHolder(private val view: View, private val onClick: (Fragment) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.api_root_title)
        private val url: TextView = view.findViewById(R.id.api_root_url)


        fun bind(homeData: HomeData) {
            val viewText = "${homeData.title}:"
            title.text = viewText
            url.text = homeData.url
            view.setOnClickListener {
                onClick(homeData.fragment)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_api_root, parent, false)

        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(apiRootList[position])
    }

    override fun getItemCount() = apiRootList.size
}

