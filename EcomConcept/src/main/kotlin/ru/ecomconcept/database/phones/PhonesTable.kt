package ru.ecomconcept.database.phones

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import ru.ecomconcept.listToString
import ru.ecomconcept.stringToList

object PhonesTable: Table("phones") {
    private val id = PhonesTable.varchar("id", 100)
    private val CPU = PhonesTable.varchar("cpu", 25)
    private val camera = PhonesTable.varchar("camera", 25)
    private val price = PhonesTable.double("price")
    private val rating = PhonesTable.double("rating").nullable()
    private val ssd = PhonesTable.varchar("ssd", 25)
    private val title = PhonesTable.varchar("title", 100)
    private val capacity = PhonesTable.varchar("capacity", 25)
    private val color = PhonesTable.varchar("color", 50)
    private val images = PhonesTable.varchar("images", 100)

    fun insert(phoneDTO: PhoneDTO){
        transaction {
            PhonesTable.insert {
                it[id] = phoneDTO.id
                it[CPU] = phoneDTO.CPU
                it[camera] = phoneDTO.camera
                it[price] = phoneDTO.price
                it[rating] = phoneDTO.rating
                it[ssd] = phoneDTO.ssd
                it[title] = phoneDTO.title
                it[capacity] = listToString(phoneDTO.capacity)
                it[color] = listToString(phoneDTO.color)
                it[images] = listToString(phoneDTO.images)
            }
        }
    }

    fun fetchPhones(): List<PhoneDTO> {
        return try {
            transaction {
                PhonesTable.selectAll().toList()
                    .map {
                        PhoneDTO(
                            id = it[PhonesTable.id],
                            CPU = it[CPU],
                            camera = it[camera],
                            price = it[price],
                            rating = it[rating],
                            ssd = it[ssd],
                            title = it[title],
                            capacity = stringToList(it[capacity]),
                            color = stringToList(it[color]),
                            images = stringToList(it[images])
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun deletePhone(id: String) {
        transaction {
            PhonesTable.deleteWhere { PhonesTable.id eq id }
        }
    }

    fun fetchPhoneById(id: String): PhoneDTO {
        return transaction {
            PhonesTable.select { PhonesTable.id eq id }.limit(1).single().let {
                PhoneDTO(
                    id = it[PhonesTable.id],
                    CPU = it[CPU],
                    camera = it[camera],
                    price = it[price],
                    rating = it[rating],
                    ssd = it[ssd],
                    title = it[title],
                    capacity = stringToList(it[capacity]),
                    color = stringToList(it[color]),
                    images = stringToList(it[images])
                )
            }
        }
    }
}