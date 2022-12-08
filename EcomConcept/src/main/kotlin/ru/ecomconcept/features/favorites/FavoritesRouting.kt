package ru.ecomconcept.features.favorites

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureFavoritesRouting() {

    routing {
        get("/favorites/get") {
            FavoritesController(call).fetchFavorite()
        }

        get("/favorites/{id?}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            FavoritesController(call).fetchFavoriteByIdUser(id)
        }

        post("/favorites/create") {
            FavoritesController(call).createFavorite()
        }

        delete("/favorites/{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            FavoritesController(call).deleteFavorite(id)
        }
    }
}