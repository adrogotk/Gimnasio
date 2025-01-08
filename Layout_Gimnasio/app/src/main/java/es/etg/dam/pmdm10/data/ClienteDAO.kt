package es.etg.dam.pmdm10.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ClienteDao {

    @Query("SELECT id, nombre, poblacion FROM cliente")
    suspend fun getAll(): List<ClienteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cliente: ClienteEntity): Int

    @Delete
    suspend fun delete(cliente: ClienteEntity)
}