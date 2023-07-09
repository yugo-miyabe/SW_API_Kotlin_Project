package com.sw.sw_api_kotlin_project.screen.webview

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.databinding.ActivityWebViewBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * WebView
 */
@AndroidEntryPoint
class WebViewActivity : AppCompatActivity() {
    private var _binding: ActivityWebViewBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val args: WebViewActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val webViewInfo = args.webViewInfo
        binding.webViewAppbar.findViewById<MaterialToolbar>(R.id.toolbar).apply {
            setOnClickListener {
                finish()
            }
            title = webViewInfo.title
        }
        binding.webView.loadUrl(webViewInfo.url)
        binding.webView.setOnKeyListener { _, keyCode, event ->
            (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN).apply {
                binding.webView.goBack()
            }
        }
        binding.webView.isFocusableInTouchMode = true
        binding.webView.requestFocus()
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
