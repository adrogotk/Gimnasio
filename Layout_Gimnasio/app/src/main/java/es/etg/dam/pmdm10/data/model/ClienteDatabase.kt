package es.etg.dam.pmdm10.data.model

import androidx.room.Database
import androidx.room.RoomDatabase
import dagger.Provides
import es.etg.dam.pmdm10.data.dao.ClienteDao
import es.etg.dam.pmdm10.data.dao.PoblacionDao
import javax.inject.Inject

@Database(entities = [ClienteEntity::class, PoblacionEntity::class], version = 1)

abstract class ClienteDatabase: RoomDatabase() {

    abstract fun clienteDao(): ClienteDao
    abstract fun poblacionDao(): PoblacionDao
}