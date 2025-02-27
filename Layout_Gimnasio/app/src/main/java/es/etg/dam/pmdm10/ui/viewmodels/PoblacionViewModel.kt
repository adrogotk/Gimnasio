package es.etg.dam.pmdm10.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import es.etg.dam.pmdm10.domain.model.Poblacion
import es.etg.dam.pmdm10.domain.usecase.GetPoblacionUseCase
import es.etg.dam.pmdm10.domain.usecase.InsertPoblacionUseCase
import es.etg.dam.pmdm10.domain.usecase.GetPoblacionPorNombreUseCase
import javax.inject.Inject

@HiltViewModel
class PoblacionViewModel @Inject constructor(
    private val getPoblacionUseCase: GetPoblacionUseCase,
    private val getPoblacionPorNombreUseCase: GetPoblacionPorNombreUseCase,
    private val insertPoblacionUseCase: InsertPoblacionUseCase
): ViewModel() {

    val poblacion= MutableLiveData<Poblacion>()

    suspend fun leerPoblacion(id: Long): Poblacion{
        val poblacionObject = getPoblacionUseCase.invoke(id)
        poblacion.postValue(poblacionObject)
        return poblacionObject
    }

    suspend fun getPoblacionPorNombre(nombre: String): Poblacion{
        val poblacionObject = getPoblacionPorNombreUseCase.invoke(nombre)
        poblacion.postValue(poblacionObject)
        return poblacionObject
    }

    suspend fun guardarPoblacion(poblacionObject: Poblacion): Long{
        val insert=insertPoblacionUseCase.invoke(poblacionObject)
        poblacion.postValue(poblacionObject)
        return insert
    }
}