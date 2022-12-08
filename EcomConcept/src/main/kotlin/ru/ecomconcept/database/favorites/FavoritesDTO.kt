package ru.ecomconcept.database.favorites

import kotlinx.serialization.Serializable
import ru.ecomconcept.database.bestsellers.BestSellersDTO
import ru.ecomconcept.features.phones.models.BestSellersRequest
import ru.ecomconcept.features.phones.models.BestSellersResponse
import ru.ecomconcept.features.phones.models.FavoritesRequest
import ru.ecomconcept.features.phones.models.FavoritesResponse
import java.util.*

@Serializable
data class FavoritesDTO(
    var id: String,
    val idUser: String,
    val idPhone: String
)

fun FavoritesRequest.mapToFavoritesDTO(): FavoritesDTO =
    FavoritesDTO(
        id = UUID.randomUUID().toString(),
        idUser = idUser,
        idPhone = idPhone
    )

fun FavoritesDTO.mapToFavoritesResponse(): FavoritesResponse =
    FavoritesResponse(
        id = id,
        idUser = idUser,
        idPhone = idPhone
    )