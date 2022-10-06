package com.sw.sw_api_kotlin_project.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    private val FORMAT_YYYYMMDDHHMMSS = "yyyy/MM/dd HH:mm"

    fun getDateStringYYYYMMDDHHMMSS(): String {
        return getDateStringYYYYMMDDHHMMSS(Date())
    }

    /**
     * yyyymmddhhmmss形式で現在日付の取得
     * @return yyyymmddhhmmss形式の日付
     */
    private fun getDateStringYYYYMMDDHHMMSS(date: Date): String {
        val format = SimpleDateFormat(FORMAT_YYYYMMDDHHMMSS, Locale.getDefault())
        return format.format(date)
    }


}