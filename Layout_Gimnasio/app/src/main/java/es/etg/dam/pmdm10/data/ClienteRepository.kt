package es.etg.dam.pmdm10.data

import es.etg.dam.pmdm10.data.model.ClienteDatabase
import es.etg.dam.pmdm10.data.model.ClienteEntity
import es.etg.dam.pmdm10.domain.model.Cliente
import es.etg.dam.pmdm10.domain.model.Poblacion
import es.etg.dam.pmdm10.ui.views.SecondActivity
import javax.inject.Inject

public class ClienteRepository @Inject constructor(
    private val clienteDatabase:ClienteDatabase

    //Aquí podría añadir dos atributos: retrofit y room
){
    val clienteDao=clienteDatabase.clienteDao()
    suspend fun leer(id: Long): Cliente{
        val cliente_entity=clienteDao.getClienteById(id).firstOrNull()
        var cliente: Cliente = Cliente(0, "",0)
        if(cliente_entity!=null) {
            cliente = Cliente(cliente_entity.id, cliente_entity.nombre, cliente_entity.poblacion)
        }
        return cliente
    }

    suspend fun getClientePorNombre(nombre: String): Cliente {
        val cliente_entity=clienteDao.getCliente(nombre).firstOrNull()
        var cliente: Cliente = Cliente(0, "",0)
        if(cliente_entity!=null) {
            cliente = Cliente(cliente_entity.id, cliente_entity.nombre, cliente_entity.poblacion)
        }
        return cliente
    }

    suspend fun guardar(cliente: Cliente): Long {
        val cliente_entity: ClienteEntity = ClienteEntity(0, cliente.nombre, cliente.poblacion)
        val insert=clienteDao.insert(cliente_entity)
        return insert
    }

}