package es.etg.dam.pmdm10.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.etg.dam.pmdm10.MainActivity.Companion.database
import es.etg.dam.pmdm10.SecondActivity

class ClienteViewModel: ViewModel() {
    val cliente= MutableLiveData<ClienteEntity>()
    suspend fun leerPersona(id: Long){
        val clienteDao = database.clienteDao()
        val persona = clienteDao.getClienteById(id)
        cliente.postValue(persona.get(SecondActivity.ZERO))
    }
}