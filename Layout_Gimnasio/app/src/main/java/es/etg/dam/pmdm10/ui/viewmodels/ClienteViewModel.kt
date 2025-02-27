package es.etg.dam.pmdm10.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import es.etg.dam.pmdm10.domain.model.Cliente
import es.etg.dam.pmdm10.domain.usecase.GetClientePorNombreUseCase
import es.etg.dam.pmdm10.domain.usecase.GetClienteUseCase
import es.etg.dam.pmdm10.domain.usecase.InsertClienteUseCase
import javax.inject.Inject

@HiltViewModel
class ClienteViewModel @Inject constructor(
    private val getClienteUseCase: GetClienteUseCase,
    private val getClientePorNombreUseCase: GetClientePorNombreUseCase,
    private val insertClienteUseCase: InsertClienteUseCase
): ViewModel() {
    val cliente= MutableLiveData<Cliente>()
    suspend fun leerPersona(id: Long): Cliente{
        val persona = getClienteUseCase.invoke(id)
        cliente.postValue(persona)
        return persona
    }

    suspend fun getPersonaPorNombre(nombre: String): Cliente{
        val personaObject = getClientePorNombreUseCase.invoke(nombre)
        cliente.postValue(personaObject)
        return personaObject
    }

    suspend fun guardarPersona(clienteObject: Cliente): Long{
        val insert=insertClienteUseCase.invoke(clienteObject)
        cliente.postValue(clienteObject)
        return insert
    }
}