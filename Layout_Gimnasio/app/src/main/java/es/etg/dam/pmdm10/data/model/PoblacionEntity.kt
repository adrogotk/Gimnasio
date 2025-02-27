package es.etg.dam.pmdm10.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "poblacion")
data class PoblacionEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0,
    var nombre:String = "",
)
