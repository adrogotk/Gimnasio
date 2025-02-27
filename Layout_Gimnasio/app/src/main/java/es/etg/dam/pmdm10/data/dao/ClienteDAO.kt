package es.etg.dam.pmdm10.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import dagger.Provides
import es.etg.dam.pmdm10.data.model.ClienteEntity
import es.etg.dam.pmdm10.domain.model.Cliente

@Dao
interface ClienteDao {

    @Query("SELECT id, nombre, poblacion FROM cliente")
    suspend fun getAll(): List<ClienteEntity>

    @Query("SELECT id, nombre, poblacion FROM cliente WHERE nombre=:nombre")
    suspend fun getCliente(nombre: String): List<ClienteEntity>

    @Query("SELECT id, nombre, poblacion FROM cliente WHERE id=:id")
    suspend fun getClienteById(id: Long): List<ClienteEntity>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cliente: ClienteEntity): Long

    @Delete
    suspend fun delete(cliente: ClienteEntity)
}