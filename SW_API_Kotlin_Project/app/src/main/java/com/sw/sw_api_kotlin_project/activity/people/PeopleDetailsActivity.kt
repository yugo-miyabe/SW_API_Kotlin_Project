package com.sw.sw_api_kotlin_project.activity.people

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.sw.sw_api_kotlin_project.data.model.People
import com.sw.sw_api_kotlin_project.databinding.ActivityPeopleDetailsBinding
import com.sw.sw_api_kotlin_project.utils.PeopleNavListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleDetailsActivity : AppCompatActivity(), PeopleNavListener {
    private var _binding: ActivityPeopleDetailsBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val args: PeopleDetailsActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPeopleDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun getPeopleValue(): People = args.peopleDetails
}