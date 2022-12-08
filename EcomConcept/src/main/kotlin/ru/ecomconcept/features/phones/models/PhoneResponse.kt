package ru.ecomconcept.features.phones.models

import kotlinx.serialization.Serializable

@Serializable
data class PhoneResponse(
    var id: String,
    val CPU: String,
    val camera: String,
    val price: Double,
    val rating: Double? = null,
    val ssd: String,
    val title: String,
    val capacity: List<String>,
    val color: List<String>,
    val images: List<String>
)