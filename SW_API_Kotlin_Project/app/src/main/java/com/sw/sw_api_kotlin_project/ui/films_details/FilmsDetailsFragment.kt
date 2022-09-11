package com.sw.sw_api_kotlin_project.ui.films_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.data.model.Films
import com.sw.sw_api_kotlin_project.databinding.FragmentFilmsDetailsBinding

class FilmsDetailsFragment : BaseFragment() {
    private lateinit var viewModel: FilmsDetailsViewModel
    private var _binding: FragmentFilmsDetailsBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val args: FilmsDetailsFragmentArgs by navArgs()
    private lateinit var films: Films

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(this, FilmsDetailsFactory())[FilmsDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        super.initView()
        films = args.films
        films.run {
            binding.titleText.text = title
            binding.releaseDateText.text = releaseDate
            binding.openingCrawlText.text = openingCrawl
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}