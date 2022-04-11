package com.sw.sw_api_kotlin_project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.base.BaseFragment


class PeopleListFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_people_list, container, false)
    }

}