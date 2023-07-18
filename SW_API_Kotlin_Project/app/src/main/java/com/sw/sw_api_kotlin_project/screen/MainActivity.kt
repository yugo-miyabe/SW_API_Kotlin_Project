package com.sw.sw_api_kotlin_project.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    private var _binding: ActivityMainBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.home_list, R.id.search, R.id.favorite_list, R.id.other)
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.bottomNavigationView.apply {
            setupWithNavController(navController)
            setOnItemReselectedListener { item ->
                val menuItemIdToFragmentIdMap = mapOf(
                    R.id.nav_home_list to R.id.nav_home_list,
                    R.id.nav_search to R.id.search,
                    R.id.nav_favorite_list to R.id.favorite_list,
                    R.id.nav_other to R.id.other,
                )
                val rootDestinationIds = menuItemIdToFragmentIdMap.values
                val currentId = navController.currentDestination?.id
                    ?: return@setOnItemReselectedListener
                val rootId = menuItemIdToFragmentIdMap[item.itemId]
                    ?: return@setOnItemReselectedListener

                if (currentId in rootDestinationIds) {
                    // 親画面だった時の処理
                    return@setOnItemReselectedListener
                } else {
                    // 子画面だった時の処理
                    navController.popBackStack(rootId, false)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}
