package com.sw.sw_api_kotlin_project.screen.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.databinding.FragmentOthersBinding
import com.sw.sw_api_kotlin_project.screen.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * その他画面
 */
@AndroidEntryPoint
class OthersFragment : BaseFragment<OthersViewModel, FragmentOthersBinding>() {
    override val viewModel: OthersViewModel by viewModels()

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentOthersBinding =
        FragmentOthersBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val swApiDocumentation = "https://swapi.dev/documentation"

        binding.othersAppbar.findViewById<MaterialToolbar>(R.id.toolbar).title =
            getString(R.string.navigation_others)
        binding.deleteFavorite.setOnClickListener {
            viewModel.delete()

            viewModel.addToastEvent(getString(R.string.other_delete_favorite_item))

        }

        binding.webViewDocumentation.setOnClickListener {
            viewModel.onTapDirection(getString(R.string.other_api_document))
        }
    }
}
