package es.etg.dam.pmdm10.data

import androidx.room.PrimaryKey

data class PoblacionEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var nombre:String = "",
)
