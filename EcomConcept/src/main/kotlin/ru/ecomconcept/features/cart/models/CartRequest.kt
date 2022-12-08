package ru.ecomconcept.features.phones.models

import kotlinx.serialization.Serializable

@Serializable
data class CartRequest(
    val idUser: String,
    val idPhone: String,
    val count: Int = 1
)