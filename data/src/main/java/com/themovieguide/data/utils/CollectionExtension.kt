package com.themovieguide.data.utils

import android.content.Context
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.themovieguide.data.model.meta.ErrorMeta
import com.themovieguide.domain.model.MetaMessage
import com.themovieguide.domain.model.Movies
import com.themovieguide.domain.states.showing.StateRawMeta
import java.io.IOException

@TypeConverter
inline fun <reified T : Any?> List<T>.castToString(): String {
    require(this.isNotEmpty()) { THROW_LIST_EXCEPTION }
    return Gson().toJson(this)
}

@TypeConverter
inline fun <reified T : Any?> String.castToList(): List<T> {
    require(this.isNotEmpty()) { THROW_STRING_EXCEPTION }
    return Gson().fromJson(this, object : TypeToken<List<T?>?>() {}.type)
}

@TypeConverter
inline fun <reified T : Any?> String.castToMutableList(): MutableList<T> {
    require(this.isNotEmpty()) { THROW_STRING_EXCEPTION }
    return Gson().fromJson(this, object : TypeToken<MutableList<T?>?>() {}.type)
}

@TypeConverter
inline fun <reified T : Any?> T.castToJson(): String {
    return Gson().toJson(this) ?: EMPTY_RESPONSE
}

@TypeConverter
fun String.castToMap(): Map<String, String> {
    val map = this.replace("{", "")
    val map1 = map.replace("}", "")
    return map1.split(",")
        .map { it.split("=") }
        .map { it.first() to it.last() }
        .toMap()
}

@TypeConverter
inline fun <reified T : Any?> String.castToClass(): T? {
    require(this.isNotEmpty()) { THROW_LIST_EXCEPTION }
    return Gson().fromJson(this, T::class.java) ?: null
}

inline fun <reified T : Any> List<T>.castToListUpdate(newValue: T, block: (T) -> Boolean): List<T> {
    require(this.isNotEmpty()) { THROW_LIST_EXCEPTION }
    return map {
        if (block(it)) newValue else it
    }
}

@TypeConverter
fun String.castToStringList(): List<String> {
    require(this.isNotEmpty()) { THROW_STRING_EXCEPTION }
    return this.split(",").toList()
}

@Throws(IOException::class)
fun Context.readJsonAsset(fileName: String): String {
    val inputStream = assets.open(fileName)
    val size = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    inputStream.close()
    return String(buffer, Charsets.UTF_8)
}

fun String.checkJsonFormat() {
    val gson = Gson()
    try {
        val jsonObject = gson.fromJson(this, JsonObject::class.java)
        if (jsonObject.has("status_code") && jsonObject.has("status_message") && jsonObject.has("success")) {
            val errorMeta = this.castToClass<ErrorMeta>()
            StateRawMeta.OnFailedMessage(data = errorMeta?.toMetaMessage() ?: MetaMessage())
        } else if (jsonObject.isJsonArray) {
            val data = this.castToList<Movies>()
            StateRawMeta.OnSuccess(data = data)
        } else {
            StateRawMeta.OnFailed(error = this)
        }
    } catch (e: Exception) {
        StateRawMeta.OnFailed(error = e.message)
    }
}

fun ErrorMeta.toMetaMessage(): MetaMessage {
    return MetaMessage(
        statusCode = this.status_code,
        statusMessage = this.status_message,
        success = this.success,
    )
}

const val EMPTY_RESPONSE = ""
const val THROW_LIST_EXCEPTION = "Not a valid Array!"
const val THROW_STRING_EXCEPTION = "Parameter must not be empty!"
