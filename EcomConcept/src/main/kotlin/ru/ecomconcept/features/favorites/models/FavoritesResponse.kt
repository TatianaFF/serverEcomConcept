package ru.ecomconcept.features.phones.models

import kotlinx.serialization.Serializable

@Serializable
data class FavoritesResponse(
    var id: String,
    val idUser: String,
    val idPhone: String
)