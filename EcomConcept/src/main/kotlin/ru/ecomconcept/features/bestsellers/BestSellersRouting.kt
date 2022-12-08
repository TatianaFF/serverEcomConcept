package ru.ecomconcept.features.bestsellers

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureBestSellersRouting() {

    routing {
        get("/bestsellers/get") {
            BestSellersController(call).fetchBestSellers()
        }

        get("/bestsellers/{id?}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            BestSellersController(call).fetchBestSellersByIdPhone(id)
        }

        post("/bestsellers/create") {
            BestSellersController(call).createBestSellers()
        }

        delete("/bestsellers/{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            BestSellersController(call).deleteBestSellers(id)
        }
    }
}