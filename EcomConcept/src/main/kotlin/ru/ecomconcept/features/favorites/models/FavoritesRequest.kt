package ru.ecomconcept.features.phones.models

import kotlinx.serialization.Serializable

@Serializable
data class FavoritesRequest(
    val idUser: String,
    val idPhone: String
)