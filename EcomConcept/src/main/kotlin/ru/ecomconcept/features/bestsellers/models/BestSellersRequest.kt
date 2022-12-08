package ru.ecomconcept.features.phones.models

import kotlinx.serialization.Serializable

@Serializable
data class BestSellersRequest(
    val idPhone: String,
    val discountPrice: Double
)