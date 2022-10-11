package com.sw.sw_api_kotlin_project.ui.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.data.database.FavoriteDatabase
import com.sw.sw_api_kotlin_project.data.model.WebViewInfo
import com.sw.sw_api_kotlin_project.databinding.FragmentOthersBinding
import com.sw.sw_api_kotlin_project.repository.FavoriteRepository
import com.sw.sw_api_kotlin_project.utils.WebViewURL

class OthersFragment : BaseFragment() {
    private lateinit var viewModel: OthersViewModel
    private var _binding: FragmentOthersBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this, OthersViewModelFactory(
                FavoriteRepository(
                    FavoriteDatabase.getDatabase(activity?.application!!).FavoriteDao(),
                ),
            )
        )[OthersViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOthersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        super.initView()
        binding.othersAppbar.findViewById<MaterialToolbar>(R.id.toolbar).title =
            getString(R.string.navigation_others)
        binding.deleteFavorite.setOnClickListener {
            viewModel.delete()
            Toast.makeText(
                context, getString(R.string.other_delete_favorite_item), Toast.LENGTH_SHORT
            ).show()
        }
        binding.webViewDocumentation.setOnClickListener {
            val action = OthersFragmentDirections.actionNavOtherToWebView(
                WebViewInfo(
                    title = getString(R.string.other_api_document),
                    url = WebViewURL.swApiDocumentation,
                )
            )
            findNavController().navigate(action)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}