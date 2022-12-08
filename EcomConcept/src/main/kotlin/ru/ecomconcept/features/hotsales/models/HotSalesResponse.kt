package ru.ecomconcept.features.phones.models

import kotlinx.serialization.Serializable

@Serializable
data class HotSalesResponse(
    var id: String,
    val idPhone: String,
    val isNew: Boolean = false,
    val subTitle: String,
    val picture: String = ""
)