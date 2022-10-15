package com.sw.sw_api_kotlin_project.activity.people_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sw.sw_api_kotlin_project.databinding.ActivityPeopleListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleListActivity : AppCompatActivity() {
    private var _binding: ActivityPeopleListBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPeopleListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}