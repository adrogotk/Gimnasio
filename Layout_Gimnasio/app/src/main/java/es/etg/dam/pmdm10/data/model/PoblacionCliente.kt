package es.etg.dam.pmdm10.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class PoblacionCliente(
    @Embedded val poblacion: PoblacionEntity,
    @Relation(
        parentColumn = "id", //Entidad cliente
        entityColumn = "poblacion" // Entidad secundaria: telefono

    )
    val cliente: List<ClienteEntity>
)
