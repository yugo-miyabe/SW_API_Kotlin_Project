package com.sw.sw_api_kotlin_project.screen

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = checkNotNull(_binding)

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashAnimation()

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.bottomNavigationView
        val navController = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        bottomNavigationView.setupWithNavController((navController!!.findNavController()))
    }


    @RequiresApi(Build.VERSION_CODES.S)
    private fun splashAnimation() {
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val slideUp = ObjectAnimator.ofFloat(
                splashScreenView,
                View.TRANSLATION_Y,
                0f,
                -splashScreenView.height.toFloat()
            )
            slideUp.interpolator = AnticipateInterpolator()
            slideUp.duration = 200L
            slideUp.doOnEnd { splashScreenView.remove() }
            slideUp.start()
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}
