package es.etg.dam.pmdm10.data

import es.etg.dam.pmdm10.data.model.ClienteDatabase
import es.etg.dam.pmdm10.data.model.ClienteEntity
import es.etg.dam.pmdm10.data.model.PoblacionEntity
import es.etg.dam.pmdm10.domain.model.Cliente
import es.etg.dam.pmdm10.domain.model.Poblacion
import es.etg.dam.pmdm10.ui.views.SecondActivity
import javax.inject.Inject

class PoblacionRepository @Inject constructor(
    private val clienteDatabase: ClienteDatabase

){
    val poblacionDao=clienteDatabase.poblacionDao()
    suspend fun leer(id: Long): Poblacion {
        val poblacion_entity=poblacionDao.getPoblacion(id).firstOrNull()
        var poblacion: Poblacion = Poblacion(0, "")
        if(poblacion_entity!=null) {
            val poblacion_object=poblacion_entity.poblacion
            poblacion = Poblacion(poblacion_object.id, poblacion_object.nombre)
        }
        return poblacion
    }

    suspend fun getPoblacionPorNombre(nombre: String): Poblacion {
        val poblacion_entity=poblacionDao.getPoblacionNombre(nombre).firstOrNull()
        var poblacion: Poblacion = Poblacion(0, "")
        if(poblacion_entity!=null) {
            poblacion = Poblacion(poblacion_entity.id, poblacion_entity.nombre)
        }
        return poblacion
    }

    suspend fun guardar(poblacion: Poblacion): Long {
        val poblacion_entity: PoblacionEntity = PoblacionEntity(0, poblacion.nombre)
        val insert=poblacionDao.insert(poblacion_entity)
        return insert
    }

}
