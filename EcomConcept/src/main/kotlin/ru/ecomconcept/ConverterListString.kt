package ru.ecomconcept

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

private val gson = Gson()

fun stringToList(data: String?): List<String> {
    return if (data == null) {
        Collections.emptyList()
    } else {
        val listType = object : TypeToken<List<String>>() {

        }.type

        gson.fromJson(data, listType)
    }
}

fun listToString(someObjects: List<String>): String {
    return gson.toJson(someObjects)
}