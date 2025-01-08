package es.etg.dam.pmdm10.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ClienteEntity::class, PoblacionEntity::class], version = 1)
abstract class ClienteDatabase: RoomDatabase() {

    abstract fun clienteDao(): ClienteDao
    abstract fun poblacionDao(): PoblacionDao
}