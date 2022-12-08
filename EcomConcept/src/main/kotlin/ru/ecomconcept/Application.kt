package ru.ecomconcept

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database
import ru.ecomconcept.features.bestsellers.configureBestSellersRouting
import ru.ecomconcept.features.cart.configureCartRouting
import ru.ecomconcept.features.favorites.configureFavoritesRouting
import ru.ecomconcept.features.hotsales.configureHotSalesRouting
import ru.ecomconcept.features.phones.configurePhoneRouting
import ru.ecomconcept.plugins.*

fun main() {
    Database.connect(url = "jdbc:postgresql://localhost:5432/testDB", driver = "org.postgresql.Driver", user = "postgres", password = "robot18")
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureSerialization()
    configurePhoneRouting()
    configureHotSalesRouting()
    configureBestSellersRouting()
    configureCartRouting()
    configureFavoritesRouting()
}
