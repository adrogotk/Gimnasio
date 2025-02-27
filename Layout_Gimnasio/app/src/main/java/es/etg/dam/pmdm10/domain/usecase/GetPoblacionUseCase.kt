package es.etg.dam.pmdm10.domain.usecase

import es.etg.dam.pmdm10.data.PoblacionRepository
import es.etg.dam.pmdm10.domain.model.Poblacion
import javax.inject.Inject

class GetPoblacionUseCase @Inject constructor(
    private val repository: PoblacionRepository
) {
    suspend operator fun invoke(id: Long): Poblacion {
        val poblacion = repository.leer(id)
        return poblacion
    }
}