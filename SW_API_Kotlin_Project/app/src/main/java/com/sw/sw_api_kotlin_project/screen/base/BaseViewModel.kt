package com.sw.sw_api_kotlin_project.screen.base

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel : ViewModel() {
    sealed interface UiEvent {
        data class Navigate(val navDirections: NavDirections) : UiEvent
        data class Dialog(val isDoShowing: Boolean) : UiEvent
        data class ToastMessage(val message: String) : UiEvent
    }

    private val _uiEventList = MutableStateFlow<List<UiEvent>>(emptyList())
    val uiEventList: StateFlow<List<UiEvent>> = _uiEventList

    fun addNavigationEvent(destination: NavDirections) {
        _uiEventList.update { uiEventList ->
            uiEventList + UiEvent.Navigate(destination)
        }
    }

    fun consumeEvent(target: UiEvent) {
        _uiEventList.update { uiEventList ->
            uiEventList.filterNot { uiEvent ->
                uiEvent == target
            }
        }
    }
}
