package com.sw.sw_api_kotlin_project.screen.base

import androidx.lifecycle.ViewModel
import com.sw.sw_api_kotlin_project.utils.PageType

abstract class BaseViewModel : ViewModel() {
    var page: Int = 1

    fun pageParameterFormat(pageType: PageType) {
        when (pageType) {
            PageType.FIRST_PAGE -> {
                page = 1
            }
            PageType.NEXT_PAGE -> {
                page += 1
            }
            PageType.PREVIOUS_PAGE -> {
                page -= 1
            }
            PageType.CURRENT_PAGE -> {
                // 何もしない
            }
        }
    }
}