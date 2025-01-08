package es.etg.dam.pmdm10.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface PoblacionDao {

    @Query("SELECT * FROM cliente WHERE id=:id")
    suspend fun getPoblacion(id: Int): List<PoblacionCliente>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(poblacion: PoblacionEntity):Int
}