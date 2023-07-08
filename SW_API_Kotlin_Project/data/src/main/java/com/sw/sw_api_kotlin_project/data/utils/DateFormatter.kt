package com.sw.sw_api_kotlin_project.data.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
object DateFormatter {
    private val formatter = SimpleDateFormat("yyyy/MM/dd HH:mm")

    fun getTodayDateStringYYYYMMDDHHMMSS(): String {
        return getDateStringYYYYMMDDHHMMSS(Date())
    }

    private fun getDateStringYYYYMMDDHHMMSS(date: Date): String {
        return formatter.format(date)
    }
}