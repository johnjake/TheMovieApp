package com.themovieguide.data.utils

import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.serialization.ExperimentalSerializationApi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@ExperimentalSerializationApi
fun json(): Gson = GsonBuilder().apply {
    setLenient()
}.create()

@TypeConverters
fun String.castToLongDate(): String {
    val param = if (this.isNotEmpty()) this.toLong() else 1685747400000L
    val date = Date(param)
    val dateFormat = SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.ENGLISH)
    return dateFormat.format(date)
}

@TypeConverters
fun convertDateTimeToLong(dateTimeString: String): Long {
    val dateFormat = SimpleDateFormat("M/d/yyyy h:mm a", Locale.getDefault())
    val date = dateFormat.parse(dateTimeString)
    return date?.time ?: 0L
}

const val SUCCESS = "success"

