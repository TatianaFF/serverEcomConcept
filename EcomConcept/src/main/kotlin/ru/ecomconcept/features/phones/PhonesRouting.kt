package ru.ecomconcept.features.phones

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configurePhoneRouting() {

    routing {
        get("/phones/get") {
            PhonesController(call).fetchPhones()
        }

        get("/phones/{id?}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            PhonesController(call).fetchPhoneById(id)
        }

        post("/phones/create") {
            PhonesController(call).createPhone()
        }

        delete("/phones/{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            PhonesController(call).deletePhone(id)
        }
    }
}