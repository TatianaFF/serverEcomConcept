package ru.ecomconcept.features.phones.models

import kotlinx.serialization.Serializable

@Serializable
data class HotSalesRequest(
    val idPhone: String,
    val isNew: Boolean = false,
    val subTitle: String,
    val picture: String = ""
)