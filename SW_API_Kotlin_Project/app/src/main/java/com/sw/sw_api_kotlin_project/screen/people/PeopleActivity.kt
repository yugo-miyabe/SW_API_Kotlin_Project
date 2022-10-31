package com.sw.sw_api_kotlin_project.screen.people

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sw.sw_api_kotlin_project.databinding.ActivityPeopleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleActivity : AppCompatActivity() {
    private var _binding: ActivityPeopleBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPeopleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}