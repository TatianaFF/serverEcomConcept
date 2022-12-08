package ru.ecomconcept.features.favorites

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.ecomconcept.database.favorites.FavoritesTable
import ru.ecomconcept.database.favorites.mapToFavoritesDTO
import ru.ecomconcept.database.favorites.mapToFavoritesResponse
import ru.ecomconcept.database.phones.PhonesTable
import ru.ecomconcept.database.phones.mapToPhoneDTO
import ru.ecomconcept.database.phones.mapToPhoneResponse
import ru.ecomconcept.features.phones.models.FavoritesRequest
import ru.ecomconcept.features.phones.models.HotSalesRequest

class FavoritesController(private val call: ApplicationCall) {
    suspend fun fetchFavorite() {
        call.respond(FavoritesTable.fetchFavorite())
    }

    suspend fun createFavorite() {
        val request = call.receive<FavoritesRequest>()
        val favorites = request.mapToFavoritesDTO()
        FavoritesTable.insert(favorites)
        call.respond(favorites.mapToFavoritesResponse())
    }

    suspend fun deleteFavorite(id: String) {
        FavoritesTable.deleteFavorite(id)
        fetchFavorite()
    }

    suspend fun fetchFavoriteByIdUser(id: String) {
        call.respond(FavoritesTable.fetchFavoriteByIdUser(id))
    }
}