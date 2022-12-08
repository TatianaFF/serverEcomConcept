package ru.ecomconcept.database.favorites

import ch.qos.logback.classic.pattern.TargetLengthBasedClassNameAbbreviator
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import ru.ecomconcept.database.cart.CartDTO
import ru.ecomconcept.database.cart.CartTable
import ru.ecomconcept.database.phones.PhonesTable

object FavoritesTable: Table("favorites") {
    private val id = FavoritesTable.varchar("id", 100)
    private val idUser = FavoritesTable.varchar("id_user", 100)
    private val idPhone = FavoritesTable.varchar("id_phone", 100)

    fun insert(favoritesDTO: FavoritesDTO) {
        transaction {
            FavoritesTable.insert {
                it[id] = favoritesDTO.id
                it[idUser] = favoritesDTO.idUser
                it[idPhone] = favoritesDTO.idPhone
            }
        }
    }

    fun fetchFavorite(): List<FavoritesDTO> {
        return try {
            transaction {
                FavoritesTable.selectAll().toList()
                    .map {
                        FavoritesDTO(
                            id = it[FavoritesTable.id],
                            idUser = it[idUser],
                            idPhone = it[idPhone]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun deleteFavorite(id: String) {
        transaction {
            FavoritesTable.deleteWhere { FavoritesTable.id eq id }
        }
    }

    fun fetchFavoriteByIdUser(id: String): List<FavoritesDTO> {
        return try {
            transaction {
                FavoritesTable.select { idUser eq id }.toList()
                    .map {
                        FavoritesDTO(
                            id = it[FavoritesTable.id],
                            idUser = it[idUser],
                            idPhone = it[idPhone]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}