package com.sw.sw_api_kotlin_project.screen.people.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.network.model.People

class PeopleListAdapter(
    private val onClick: (People) -> Unit,
) : PagingDataAdapter<People, PeopleListViewHolder>(PE0PLE_DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_common, parent, false)

        return PeopleListViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: PeopleListViewHolder, position: Int) {
        val people = getItem(position)
        if (people != null) {
            holder.bind(people)
        }
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