package ru.ecomconcept.features.cart

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureCartRouting() {

    routing {
        get("/cart/get") {
            CartController(call).fetchCart()
        }

        get("/cart/{id?}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            CartController(call).fetchCartByIdUser(id)
        }

        post("/cart/create") {
            CartController(call).createCart()
        }

        delete("/cart/{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            CartController(call).deleteCart(id)
        }

        patch("cart/update") {
            CartController(call).updateCart()
        }
    }
}