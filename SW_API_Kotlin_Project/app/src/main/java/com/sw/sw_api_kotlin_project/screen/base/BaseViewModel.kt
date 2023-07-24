package com.sw.sw_api_kotlin_project.screen.base

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel : ViewModel() {
    sealed interface UiEvent {
        data class Navigate(val navDirections: NavDirections) : UiEvent
        data class ProgressDialog(val isDoShowing: Boolean) : UiEvent
        data class ToastMessage(val message: String) : UiEvent
    }

    private val _uiEventList = MutableStateFlow<List<UiEvent>>(emptyList())
    val uiEventList: StateFlow<List<UiEvent>> = _uiEventList


    fun addProgressDialogEvent(isDoShowing: Boolean) {
        _uiEventList.update { uiEventList ->
            uiEventList + UiEvent.ProgressDialog(isDoShowing = isDoShowing)
        }
    }

    fun addNavigationEvent(destination: NavDirections) {
        _uiEventList.update { uiEventList ->
            uiEventList + UiEvent.Navigate(navDirections = destination)
        }
    }

    fun addToastEvent(message: String) {
        _uiEventList.update { uiEvents ->
            uiEvents + UiEvent.ToastMessage(message = message)
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
