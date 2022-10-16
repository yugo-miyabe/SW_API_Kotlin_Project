package com.sw.sw_api_kotlin_project.fragment.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.data.model.WebViewInfo
import com.sw.sw_api_kotlin_project.databinding.FragmentOthersBinding
import com.sw.sw_api_kotlin_project.utils.WebViewURL
import dagger.hilt.android.AndroidEntryPoint

/**
 * その他画面
 */
@AndroidEntryPoint
class OthersFragment : BaseFragment() {
    private val viewModel: OthersViewModel by viewModels()
    private var _binding: FragmentOthersBinding? = null
    private val binding get() = checkNotNull(_binding)

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
            val action = OthersFragmentDirections.actionNavOtherToNavWebView(
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