package es.etg.dam.pmdm10.domain.usecase

import es.etg.dam.pmdm10.data.PoblacionRepository
import es.etg.dam.pmdm10.domain.model.Poblacion
import javax.inject.Inject

class GetPoblacionPorNombreUseCase @Inject constructor(
    private val repository: PoblacionRepository
) {
    suspend operator fun invoke(nombre: String): Poblacion {
        val poblacion = repository.getPoblacionPorNombre(nombre)
        return poblacion
    }
}