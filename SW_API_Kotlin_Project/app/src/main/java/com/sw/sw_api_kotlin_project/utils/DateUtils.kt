package com.sw.sw_api_kotlin_project.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private val FORMAT_YYYYMMDDHHMMSS = "yyyy/MM/dd HH:mm"

    fun getTodayDateStringYYYYMMDDHHMMSS(): String {
        return getDateStringYYYYMMDDHHMMSS(Date())
    }

    private fun getDateStringYYYYMMDDHHMMSS(date: Date): String {
        val format = SimpleDateFormat(FORMAT_YYYYMMDDHHMMSS, Locale.getDefault())
        return format.format(date)
    }
}