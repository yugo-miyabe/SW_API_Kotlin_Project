package com.sw.sw_api_kotlin_project.screen.people.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.data.network.model.People

class PeopleListAdapter(
    private val onClick: (People) -> Unit,
) : PagingDataAdapter<People, PeopleListAdapter.PeopleListViewHolder>(PE0PLE_DIFF_CALLBACK) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_common, parent, false)

        return PeopleListViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: PeopleListViewHolder, position: Int) {
        val people = getItem(position)
        if (people != null) holder.bind(people)
    }

    companion object {
        private val PE0PLE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<People>() {
            override fun areItemsTheSame(oldItem: People, newItem: People): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: People, newItem: People): Boolean =
                oldItem == newItem
        }
    }

}
