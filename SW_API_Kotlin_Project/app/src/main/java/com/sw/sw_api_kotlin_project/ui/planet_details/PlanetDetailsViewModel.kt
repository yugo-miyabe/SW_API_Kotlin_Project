package com.sw.sw_api_kotlin_project.ui.planet_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sw.sw_api_kotlin_project.base.BaseViewModel
import com.sw.sw_api_kotlin_project.repository.PlanetRepository

class PlanetDetailsViewModel : BaseViewModel() {

}

class PlanetDetailsFactory(private val planetRepository: PlanetRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlanetDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlanetDetailsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}