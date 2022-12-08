package ru.ecomconcept.features.hotsales

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureHotSalesRouting() {

    routing {
        get("/hotsales/get") {
            HotSalesController(call).fetchHotSales()
        }

        get("/hotsales/{id?}") {

        }

        post("/hotsales/create") {
            HotSalesController(call).createHotSales()
        }

        delete("/hotsales/{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            HotSalesController(call).deleteHotSales(id)
        }
    }
}