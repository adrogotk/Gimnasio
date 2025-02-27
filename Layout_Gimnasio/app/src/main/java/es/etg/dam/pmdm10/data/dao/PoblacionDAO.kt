package es.etg.dam.pmdm10.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.etg.dam.pmdm10.data.model.PoblacionCliente
import es.etg.dam.pmdm10.data.model.PoblacionEntity

@Dao
interface PoblacionDao {

    @Query("SELECT * FROM poblacion WHERE id=:id")
    suspend fun getPoblacion(id: Long): List<PoblacionCliente>

    @Query("SELECT * FROM poblacion WHERE nombre=:nombre")
    suspend fun getPoblacionNombre(nombre: String): List<PoblacionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(poblacion: PoblacionEntity):Long
}