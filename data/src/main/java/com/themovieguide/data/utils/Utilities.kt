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
    val date = Date(this.toLong())
    val dateFormat = SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.ENGLISH)
    return dateFormat.format(date)
}

