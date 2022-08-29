package com.sw.sw_api_kotlin_project.base

import androidx.lifecycle.ViewModel
import com.sw.sw_api_kotlin_project.utils.PageType

open class BaseViewModel : ViewModel() {
    var page: Int = 1

    fun pageFormat(pageType: PageType) {
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
        }
    }
}