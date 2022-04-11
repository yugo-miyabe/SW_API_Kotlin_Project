package com.sw.sw_api_kotlin_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.utils.Constants

class APIRootAdapter(private val apiRootList: List<String>, private val onClick: (String) -> Unit) :
    RecyclerView.Adapter<APIRootAdapter.ViewHolder>() {

    class ViewHolder(private val view: View, private val onClick: (String) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.api_root_item)

        fun bind(url: String, text: String) {
            val viewText = "$text:$url"
            textView.text = viewText
            view.setOnClickListener {
                onClick(url)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_api_root, parent, false)

        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(apiRootList[position], Constants.API_ROOT_LIST[position])
    }

    override fun getItemCount() = apiRootList.size
}

